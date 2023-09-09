package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.hzqing.orange.admin.module.system.permission.biz.converter.PermissionConverter;
import com.hzqing.orange.admin.module.system.permission.biz.dto.RoleResourceRlQueryDTO;
import com.hzqing.orange.admin.module.system.permission.biz.entity.*;
import com.hzqing.orange.admin.module.system.permission.biz.manager.*;
import com.hzqing.orange.admin.module.system.permission.biz.service.MenuService;
import com.hzqing.orange.admin.module.system.permission.biz.service.PermissionService;
import com.hzqing.orange.admin.module.system.permission.biz.service.RoleService;
import com.hzqing.orange.admin.module.system.permission.biz.service.UserService;
import com.hzqing.orange.admin.module.system.permission.common.constants.enums.ResourceTypeEnum;
import com.hzqing.orange.admin.module.system.permission.common.vo.*;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import com.hzqing.orange.admin.starter.common.constants.CommonConstants;
import com.hzqing.orange.admin.starter.common.constants.PermissionConstants;
import com.hzqing.orange.admin.starter.common.util.CollUtils;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
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
    public List<Long> queryRoleIdByUserId(Long userId) {
        List<UserRoleRlEntity> rlEntityList = userRoleRlManager.listByUserId(userId);
        if (CollUtil.isEmpty(rlEntityList)) {
            return List.of();
        }
        return CollUtils.convertList(rlEntityList, UserRoleRlEntity::getRoleId);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean allotUserRole(AllotUserRole allotUserRole) {
        userRoleRlManager.removeByUserId(allotUserRole.getUserId());
        List<UserRoleRlEntity> rlEntityList = new ArrayList<>(allotUserRole.getRoleIds().size());
        for (Long roleId : allotUserRole.getRoleIds()) {
            rlEntityList.add(new UserRoleRlEntity(allotUserRole.getUserId(), roleId));
        }
        userRoleRlManager.batchAdd(rlEntityList);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean allotRoleResource(AllotRoleResource allotUserRoleVo) {
        Long roleId = allotUserRoleVo.getRoleId();
        roleResourceRlManager.removeByRoleId(roleId);
        log.info("clean up. roleId:{}", roleId);

        List<Long> menuIds = allotUserRoleVo.getMenuIds();
        List<Long> buttonIds = allotUserRoleVo.getButtonIds();

        log.info("roleId:{},menuIds:{},buttonIds:{}", roleId, menuIds, buttonIds);
        List<RoleResourceRlEntity> roleResourceRlEntityList = new ArrayList<>();
        menuIds.forEach(i -> {
            roleResourceRlEntityList.add(RoleResourceRlEntity.builder().roleId(roleId).resourceId(i).resourceType(ResourceTypeEnum.MENU).build());
        });
        if (CollUtil.isNotEmpty(buttonIds)) {
            buttonIds.forEach(i -> {
                roleResourceRlEntityList.add(RoleResourceRlEntity.builder().roleId(roleId).resourceId(i).resourceType(ResourceTypeEnum.BUTTON).build());
            });
        }
        roleResourceRlManager.batchAdd(roleResourceRlEntityList);
        return Boolean.TRUE;
    }

    @Override
    public ResourceIds queryResourceIdsByRoleId(Long roleId) {
        RoleResourceRlQueryDTO queryDTO = RoleResourceRlQueryDTO.builder().roleId(roleId).build();
        List<RoleResourceRlEntity> resourceRlEntityList = roleResourceRlManager.listByParams(queryDTO);
        ResourceIds resourceIdsVo = new ResourceIds();
        if (CollUtil.isEmpty(resourceRlEntityList)) {
            return resourceIdsVo;
        }
        List<Long> menuIds = resourceRlEntityList.stream().filter(i -> ResourceTypeEnum.MENU.equals(i.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
        List<Long> buttonIds =
                resourceRlEntityList.stream().filter(i -> ResourceTypeEnum.BUTTON.equals(i.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
        resourceIdsVo.setMenuIds(menuIds);
        resourceIdsVo.setButtonIds(buttonIds);
        return resourceIdsVo;
    }

    @Override
    public List<RouterTree> queryCurrentUserRoutersTree() {
        Long userId = GlobalContextHelper.getCurrentUserId();
        UserVO user = userService.getById(userId);
        if (Objects.isNull(user)) {
            log.info("user is null.userId:{}", userId);
            return List.of();
        }

        List<Role> roleList = roleService.queryByUserId(userId);
        if (CollUtil.isEmpty(roleList)) {
            return List.of();
        }
        List<String> permissions = CollUtils.convertList(roleList, Role::getPermission);
        List<Long> roleIds = CollUtils.convertList(roleList, Role::getId);
        List<Menu> menuList;
        // admin 拥有所有的权限
        if (permissions.contains(PermissionConstants.ADMIN_DEFAULT_PERMISSION)) {
            menuList = menuService.queryByParams(MenuAllQuery.builder().build());
        } else {
            menuList = menuService.queryByRoleIds(roleIds);
        }
        if (CollUtil.isEmpty(menuList)) {
            return List.of();
        }
        // 排序
        menuList = menuList.stream().sorted(Comparator.comparing(Menu::getSort)).collect(Collectors.toList());

        //组装树型结果
        List<RouterTree> routers = PermissionConverter.INSTANCE.listMenusToRouters(menuList);
        Map<Long, List<RouterTree>> routerMap = routers.stream().collect(Collectors.groupingBy(RouterTree::getParentId));
        routers.forEach(item -> item.setChildren(routerMap.get(item.getId())));
        // 过滤掉非顶级数据
        return routers.stream().filter(item -> CommonConstants.Common.DEFAULT_PARENT_ID.equals(item.getParentId())).collect(Collectors.toList());
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