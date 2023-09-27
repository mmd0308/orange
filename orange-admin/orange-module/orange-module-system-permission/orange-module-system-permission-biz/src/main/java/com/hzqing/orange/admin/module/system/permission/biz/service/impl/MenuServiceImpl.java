package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.hzqing.orange.admin.module.system.permission.biz.converter.ButtonConverter;
import com.hzqing.orange.admin.module.system.permission.biz.converter.MenuConverter;
import com.hzqing.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.dto.RoleResourceRlListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import com.hzqing.orange.admin.module.system.permission.biz.entity.MenuEntity;
import com.hzqing.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import com.hzqing.orange.admin.module.system.permission.biz.manager.ButtonManager;
import com.hzqing.orange.admin.module.system.permission.biz.manager.MenuManager;
import com.hzqing.orange.admin.module.system.permission.biz.manager.RoleResourceRlManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.MenuService;
import com.hzqing.orange.admin.module.system.permission.common.constants.enums.ResourceTypeEnum;
import com.hzqing.orange.admin.module.system.permission.common.constants.exception.MenuErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.ButtonVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTreeVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;
import com.hzqing.orange.admin.starter.common.constants.CommonConstants;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import com.hzqing.orange.admin.starter.common.exception.ServiceException;
import com.hzqing.orange.admin.starter.common.util.CollUtils;
import com.hzqing.orange.admin.starter.common.validator.Assert;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuManager menuManager;

    private final ButtonManager buttonManager;

    private final RoleResourceRlManager roleResourceRlManager;

    @Override
    public List<MenuTreeVO> queryTree(MenuTreeQuery query) {
        List<MenuEntity> entityList = menuManager.listByParams(MenuConverter.INSTANCE.toListQuery(query));
        List<MenuTreeVO> listTreeVo = MenuConverter.INSTANCE.toListTree(entityList);
        Map<Long, List<MenuTreeVO>> menuMap = listTreeVo.stream().collect(Collectors.groupingBy(MenuTreeVO::getParentId));
        List<Long> deleteSubIds = new ArrayList<>();
        // 组装子集
        listTreeVo.forEach(item -> {
            List<MenuTreeVO> subList = menuMap.get(item.getId());
            if (CollUtil.isNotEmpty(subList)) {
                deleteSubIds.addAll(CollUtils.convertList(subList, MenuTreeVO::getId));
            }
            item.setChildren(subList);
        });
        return listTreeVo.stream().filter(item -> !deleteSubIds.contains(item.getId())).collect(Collectors.toList());
    }

    @Override
    public List<MenuVO> queryByParams(MenuAllQuery query) {
        List<MenuEntity> entityList = menuManager.listByParams(MenuConverter.INSTANCE.toListQuery(query));
        List<MenuVO> menuVOS = MenuConverter.INSTANCE.toListVo(entityList);
        if (query.isIncludeButton()) {
            assemblyButton(menuVOS);
        }
        return menuVOS;
    }

    private void assemblyButton(List<MenuVO> menuVOS) {
        if (CollUtil.isEmpty(menuVOS)) {
            return;
        }
        List<Long> menuIds = menuVOS.stream().map(MenuVO::getId).distinct().collect(Collectors.toList());
        List<ButtonEntity> buttonEntityList = buttonManager.listByParams(ButtonListQuery.builder().menuIds(menuIds).build());
        List<ButtonVO> buttonVOVoList = ButtonConverter.INSTANCE.toListVo(buttonEntityList);
        if (CollUtil.isEmpty(buttonVOVoList)) {
            return;
        }
        Map<Long, List<ButtonVO>> buttonMap = buttonVOVoList.stream().collect(Collectors.groupingBy(ButtonVO::getMenuId));
        // 组装按钮
        menuVOS.forEach(item -> {
            if (buttonMap.containsKey(item.getId())) {
                item.setButtonVOList(buttonMap.get(item.getId()));
            }
        });
    }

    @Override
    public List<MenuVO> queryByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return List.of();
        }
        RoleResourceRlListQuery resourceRlQueryDTO = RoleResourceRlListQuery.builder()
                .resourceType(ResourceTypeEnum.MENU).roleIds(roleIds).build();
        List<RoleResourceRlEntity> resourceRlEntityList = roleResourceRlManager.listByParams(resourceRlQueryDTO);
        if (CollUtil.isEmpty(resourceRlEntityList)) {
            return List.of();
        }
        List<Long> menuIds = CollUtils.convertList(resourceRlEntityList, RoleResourceRlEntity::getResourceId);
        List<MenuEntity> entityList = menuManager.listByIds(menuIds);
        return MenuConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public Long add(MenuAddRequest request) {
        Assert.nonNull(request.getPermission(), MenuErrorCode.MENU_PERMISSION_CANNOT_NULL);
        List<MenuEntity> entityList = menuManager.listByParams(MenuListQuery.builder().permission(request.getPermission()).build());
        Assert.isEmpty(entityList, MenuErrorCode.MENU_PERMISSION_CANNOT_REPEAT);
        request.setParentId(Objects.isNull(request.getParentId()) ? CommonConstants.Common.DEFAULT_PARENT_ID : request.getParentId());
        MenuEntity entity = MenuConverter.INSTANCE.toEntity(request);
        return menuManager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, MenuUpdateRequest request) {
        MenuEntity entity = menuManager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstants.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(request.getPermission()) && !request.getPermission().equals(entity.getPermission())) {
            List<MenuEntity> entityList = menuManager.listByParams(MenuListQuery.builder().permission(request.getPermission()).build());
            Assert.isEmpty(entityList, MenuErrorCode.MENU_PERMISSION_CANNOT_REPEAT);
        }
        entity = MenuConverter.INSTANCE.updateConvert(entity, request);
        return menuManager.updateById(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        MenuEntity entity = menuManager.getById(id);
        Assert.nonNull(entity, GlobalErrorCodeConstants.GLOBAL_DATA_NOT_EXIST);
        // 预置数据 不允许删除
        if (DataPresetFlagEnum.PRESET.equals(entity.getPresetFlag())) {
            throw new ServiceException(GlobalErrorCodeConstants.GLOBAL_DATA_PRESET_CANNOT_DELETE);
        }
        // 存在子集菜单 不允许删除
        List<MenuEntity> entityList = menuManager.listByParams(MenuListQuery.builder().parentId(id).build());
        if (CollUtil.isNotEmpty(entityList)) {
            throw new ServiceException(MenuErrorCode.MENU_DELETE_ERROR_EXIST_SUBSET);
        }
        // 存在按钮关联按钮 不允许删除
        List<ButtonEntity> buttonEntityList = buttonManager.listByParams(ButtonListQuery.builder().menuId(id).build());
        if (CollUtil.isNotEmpty(buttonEntityList)) {
            throw new ServiceException(MenuErrorCode.MENU_DELETE_ERROR_EXIST_BUTTON);
        }
        return menuManager.removeById(id);
    }
}
