package cn.hengzq.orange.admin.module.system.permission.biz.service.impl;

import cn.hengzq.orange.admin.module.system.permission.biz.converter.PermissionConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleResourceRlListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.*;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.*;
import cn.hengzq.orange.admin.module.system.permission.biz.service.MenuService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.PermissionService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.RoleService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.admin.module.system.permission.common.vo.*;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.AllotRoleResourceRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.AllotUserRoleRequest;
import cn.hengzq.orange.admin.starter.common.constant.CommonConstant;
import cn.hengzq.orange.admin.starter.common.constant.PermissionConstant;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final UserService userService;

    private final RoleService roleService;

    private final MenuService menuService;

    private final MenuManager menuManager;

    private final ButtonManager buttonManager;

    private final RoleManager roleManager;

    private final UserRoleRlManager userRoleRlManager;

    private final RoleResourceRlManager roleResourceRlManager;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean allotUserRole(AllotUserRoleRequest allotUserRoleRequest) {
        userRoleRlManager.removeByUserId(allotUserRoleRequest.getUserId());
        List<UserRoleRlEntity> rlEntityList = new ArrayList<>(allotUserRoleRequest.getRoleIds().size());
        for (Long roleId : allotUserRoleRequest.getRoleIds()) {
            rlEntityList.add(new UserRoleRlEntity(allotUserRoleRequest.getUserId(), roleId, Boolean.TRUE));
        }
        userRoleRlManager.batchAdd(rlEntityList);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean allotRoleResource(AllotRoleResourceRequest allotUserRoleVo) {
        Long roleId = allotUserRoleVo.getRoleId();
        roleResourceRlManager.removeByRoleId(roleId);
        log.info("clean up. roleId:{}", roleId);

        List<Long> menuIds = allotUserRoleVo.getMenuIds();
        List<Long> buttonIds = allotUserRoleVo.getButtonIds();

        log.info("roleId:{},menuIds:{},buttonIds:{}", roleId, menuIds, buttonIds);
        List<RoleResourceRlEntity> roleResourceRlEntityList = new ArrayList<>();
        menuIds.forEach(i -> {
            RoleResourceRlEntity menu = new RoleResourceRlEntity(roleId, i, ResourceTypeEnum.MENU);
            menu.initParams(true);
            roleResourceRlEntityList.add(menu);
        });

        if (CollUtil.isNotEmpty(buttonIds)) {
            buttonIds.forEach(i -> {
                RoleResourceRlEntity button = new RoleResourceRlEntity(roleId, i, ResourceTypeEnum.BUTTON);
                button.initParams(true);
                roleResourceRlEntityList.add(button);
            });
        }
        roleResourceRlManager.batchAdd(roleResourceRlEntityList);
        return Boolean.TRUE;
    }

    @Override
    public ResourceIds queryResourceIdsByRoleId(Long roleId) {
        RoleResourceRlListQuery queryDTO = RoleResourceRlListQuery.builder().roleId(roleId).build();
        List<RoleResourceRlEntity> resourceRlEntityList = roleResourceRlManager.listByParams(queryDTO);
        ResourceIds resourceIds = new ResourceIds();
        if (CollUtil.isEmpty(resourceRlEntityList)) {
            return resourceIds;
        }
        List<Long> menuIds = resourceRlEntityList.stream().filter(i -> ResourceTypeEnum.MENU.equals(i.getResourceType()))
                .map(RoleResourceRlEntity::getResourceId).toList();
        List<Long> buttonIds = resourceRlEntityList.stream().filter(i -> ResourceTypeEnum.BUTTON.equals(i.getResourceType()))
                .map(RoleResourceRlEntity::getResourceId).toList();
        resourceIds.setMenuIds(menuIds);
        resourceIds.setButtonIds(buttonIds);
        return resourceIds;
    }

    @Override
    public List<RouterTreeVO> queryCurrentUserRoutersTree() {
        Long userId = GlobalContextHelper.getCurrentUserId();
        UserVO user = userService.getById(userId);
        if (Objects.isNull(user)) {
            log.info("user is null.userId:{}", userId);
            return List.of();
        }

        List<RoleVO> roleVOList = roleService.queryByUserId(userId);
        if (CollUtil.isEmpty(roleVOList)) {
            return List.of();
        }
        List<String> permissions = CollUtils.convertList(roleVOList, RoleVO::getPermission);
        List<Long> roleIds = CollUtils.convertList(roleVOList, RoleVO::getId);
        List<MenuVO> menuVOList;
        // admin 拥有所有的权限
        if (permissions.contains(PermissionConstant.ADMIN_DEFAULT_PERMISSION)) {
            menuVOList = menuService.queryByParams(new MenuAllQuery());
        } else {
            menuVOList = menuService.queryByRoleIds(roleIds);
        }
        if (CollUtil.isEmpty(menuVOList)) {
            return List.of();
        }
        // 排序
        menuVOList = menuVOList.stream().filter(item -> Objects.nonNull(item.getHidden()) && !item.getHidden())
                .sorted(Comparator.comparing(MenuVO::getSort)).collect(Collectors.toList());

        //组装树型结果
        List<RouterTreeVO> routers = PermissionConverter.INSTANCE.listMenusToRouters(menuVOList);
        Map<Long, List<RouterTreeVO>> routerMap = routers.stream().collect(Collectors.groupingBy(RouterTreeVO::getParentId));
        routers.forEach(item -> item.setChildren(routerMap.get(item.getId())));
        // 过滤掉非顶级数据
        return routers.stream().filter(item -> CommonConstant.DEFAULT_PARENT_ID.equals(item.getParentId())).collect(Collectors.toList());
    }

    @Override
    public ResourcePermissions queryAllPermissionsByUserId(Long userId) {
        ResourcePermissions result = new ResourcePermissions();
        UserVO user = userService.getById(userId);
        if (user == null) {
            log.info("user is null.userId:{}", userId);
            return result;
        }
        List<RoleEntity> roleEntities = roleManager.listByUserId(userId);
        if (CollUtil.isEmpty(roleEntities)) {
            return result;
        }
        List<String> roleCodes = CollUtils.convertList(roleEntities, RoleEntity::getPermission);
        List<String> allPermissions = new ArrayList<>(roleCodes);
        result.setRolePermissions(roleCodes);


        List<Long> roleIds = CollUtils.convertList(roleEntities, RoleEntity::getId);
        List<RoleResourceRlEntity> roleResourceRlEntities = roleResourceRlManager.listByRoleIds(roleIds);
        if (CollUtil.isEmpty(roleResourceRlEntities)) {
            result.setAllPermissions(allPermissions);
            return result;
        }
        List<Long> menuIds = roleResourceRlEntities.stream().filter(item -> ResourceTypeEnum.MENU.equals(item.getResourceType()))
                .map(RoleResourceRlEntity::getResourceId).toList();
        if (CollUtil.isNotEmpty(menuIds)) {
            List<MenuEntity> menuEntityList = menuManager.listByIds(menuIds);
            List<String> menuCodes = CollUtils.convertList(menuEntityList, MenuEntity::getPermission);
            allPermissions.addAll(menuCodes);
            result.setMenuPermissions(menuCodes);
        }

        List<Long> buttonIds = roleResourceRlEntities.stream().filter(item -> ResourceTypeEnum.BUTTON.equals(item.getResourceType()))
                .map(RoleResourceRlEntity::getResourceId).toList();
        if (CollUtil.isNotEmpty(buttonIds)) {
            List<ButtonEntity> buttonEntities = buttonManager.listByIds(buttonIds);
            List<String> buttonCodes = CollUtils.convertList(buttonEntities, ButtonEntity::getPermission);
            allPermissions.addAll(buttonCodes);
            result.setButtonPermissions(buttonCodes);
        }
        result.setAllPermissions(allPermissions);
        return result;
    }
}
