package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import  com.hzqing.orange.admin.module.system.permission.biz.converter.ButtonConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.converter.MenuConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.RoleResourceRlQueryDTO;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.MenuEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.ButtonManager;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.MenuManager;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.RoleResourceRlManager;
import  com.hzqing.orange.admin.module.system.permission.biz.service.MenuService;
import com.hzqing.orange.admin.module.system.permission.common.constants.enums.ResourceTypeEnum;
import com.hzqing.orange.admin.module.system.permission.common.constants.exception.MenuErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.Button;
import com.hzqing.orange.admin.module.system.permission.common.vo.Menu;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuButtonTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTree;
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
 *@author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuManager menuManager;

    private final ButtonManager buttonManager;

    private final RoleResourceRlManager roleResourceRlManager;

    @Override
    public List<MenuTree> queryTree(MenuTreeQuery query) {
        MenuListQuery listQuery = MenuConverter.INSTANCE.toListQuery(query);
        List<MenuEntity> entityList = menuManager.listByParams(listQuery);

        List<MenuTree> listTreeVo = MenuConverter.INSTANCE.toListTree(entityList);
        Map<Long, List<MenuTree>> menuMap = listTreeVo.stream().collect(Collectors.groupingBy(MenuTree::getParentId));
        List<Long> deleteSubIds = new ArrayList<>();
        // 组装子集
        listTreeVo.forEach(item -> {
            List<MenuTree> subList = menuMap.get(item.getId());
            if (CollUtil.isNotEmpty(subList)) {
                deleteSubIds.addAll(CollUtils.convertList(subList, MenuTree::getId));
            }
            item.setChildren(subList);
        });
        return listTreeVo.stream().filter(item -> !deleteSubIds.contains(item.getId())).collect(Collectors.toList());
    }

    @Override
    public List<MenuButtonTree> queryAllMenuAndButtonTree() {
        List<MenuEntity> menuEntityList = menuManager.listByParams(MenuListQuery.builder().build());
        List<MenuButtonTree> menuButtonTree = MenuConverter.INSTANCE.toListMenuButtonTree(menuEntityList);
        // 组装按钮
        List<ButtonEntity> buttonEntityList = buttonManager.listByParams(ButtonListQuery.builder().build());
        List<Button> buttonVoList = ButtonConverter.INSTANCE.toListVo(buttonEntityList);
        Map<Long, List<Button>> buttonMap = CollUtil.isEmpty(buttonVoList) ? null : buttonVoList.stream().collect(Collectors.groupingBy(Button::getMenuId));

        Map<Long, List<MenuButtonTree>> menuMap = menuButtonTree.stream().collect(Collectors.groupingBy(MenuButtonTree::getParentId));
        // 组装子集
        menuButtonTree.forEach(item -> {
            item.setChildren(menuMap.get(item.getId()));
            if (Objects.nonNull(buttonMap) && buttonMap.containsKey(item.getId())) {
                item.setButtonList(buttonMap.get(item.getId()));
            }
        });
        // 过滤掉非顶级数据
        return menuButtonTree.stream().filter(item -> CommonConstants.Common.DEFAULT_PARENT_ID.equals(item.getParentId())).collect(Collectors.toList());
    }

    @Override
    public List<Menu> queryByParams(MenuAllQuery query) {
        MenuListQuery listQuery = MenuConverter.INSTANCE.toListQuery(query);
        List<MenuEntity> entityList = menuManager.listByParams(listQuery);
        return MenuConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public List<Menu> queryByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return List.of();
        }
        RoleResourceRlQueryDTO resourceRlQueryDTO = RoleResourceRlQueryDTO.builder()
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


    //    @Override
//    public List<MenuButtonTree> queryMenuTreeAndButton() {
//        List<MenuEntity> menuEntityList = mapper.selectList(Wrappers.<MenuEntity>lambdaQuery().orderByDesc(MenuEntity::getSort));
//        List<MenuButtonTree> listTreeVo = converter.toListMenuButtonTreeVo(menuEntityList);
//        // 组装按钮
//        List<ButtonEntity> buttonEntityList = buttonMapper.selectList(Wrappers.emptyWrapper());
//        List<Button> buttonVoList = buttonConverter.toListVo(buttonEntityList);
//        Map<Long, List<Button>> buttonMap = CollUtil.isEmpty(buttonVoList) ? null :
//                buttonVoList.stream().collect(Collectors.groupingBy(Button::getMenuId));
//
//        Map<Long, List<MenuButtonTree>> menuMap = listTreeVo.stream().collect(Collectors.groupingBy(MenuButtonTree::getParentId));
//        // 组装子集
//        listTreeVo.forEach(item -> {
//            item.setChildren(menuMap.get(item.getId()));
//            if (Objects.nonNull(buttonMap) && buttonMap.containsKey(item.getId())) {
//                item.setButtonList(buttonMap.get(item.getId()));
//            }
//        });
//        // 过滤掉非顶级数据
//        return listTreeVo.stream().filter(item -> CommonConstant.Common.DEFAULT_PARENT_ID.equals(item.getParentId())).collect(Collectors.toList());
//    }
//
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
