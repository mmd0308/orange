package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.MenuEntity;
import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Mapper
public interface MenuConverter extends Converter {
    MenuConverter INSTANCE = Mappers.getMapper(MenuConverter.class);

    MenuEntity toEntity(MenuAddRequest request);

    MenuEntity toEntity(MenuVO menuVO);

    MenuVO toVo(MenuEntity entity);

    List<MenuVO> toListVo(List<MenuEntity> entityList);

    MenuListQuery toListQuery(MenuTreeQuery query);

    List<MenuTreeVO> toListTree(List<MenuEntity> entityList);

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

}
