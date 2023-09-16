package com.hzqing.orange.admin.module.system.permission.biz.converter;

import  com.hzqing.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.MenuEntity;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuButtonTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 *@author 程序员橙子
 */
@Mapper
public interface MenuConverter extends Converter {
    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    MenuEntity toEntity(MenuAddRequest request);

    MenuEntity toEntity(MenuVO menuVO);

    MenuVO toVo(MenuEntity entity);

    List<MenuVO> toListVo(List<MenuEntity> entityList);

    MenuListQuery toListQuery(MenuTreeQuery query);

    List<MenuTree> toListTree(List<MenuEntity> entityList);

    MenuListQuery toListQuery(MenuAllQuery query);

    default MenuEntity updateConvert(MenuEntity entity, MenuUpdateRequest request) {
        if (Objects.isNull(request) || Objects.isNull(entity)) {
            return null;
        }
        if (Objects.nonNull(request.getParentId())) {
            entity.setParentId(request.getParentId());
        }
        if (Objects.nonNull(request.getName())) {
            entity.setName(request.getName());
        }
        if (Objects.nonNull(request.getPermission())) {
            entity.setPermission(request.getPermission());
        }
        if (Objects.nonNull(request.getPath())) {
            entity.setPath(request.getPath());
        }
        if (Objects.nonNull(request.getIcon())) {
            entity.setIcon(request.getIcon());
        }
        if (Objects.nonNull(request.getComponent())) {
            entity.setComponent(request.getComponent());
        }
        if (Objects.nonNull(request.getHidden())) {
            entity.setHidden(request.getHidden());
        }
        if (Objects.nonNull(request.getSort())) {
            entity.setSort(request.getSort());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }

    List<MenuButtonTree> toListMenuButtonTree(List<MenuEntity> menuEntityList);
}
