package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RoleVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.RolePageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.RoleAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.RoleUpdateRequest;
import cn.hengzq.orange.admin.starter.common.converter.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 *@author 程序员橙子
 */
@Mapper
public interface RoleConverter extends Converter {

    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    List<RoleVO> toListVo(List<RoleEntity> roleList);

    RoleEntity toEntity(RoleVO roleVO);

    RoleVO toVo(RoleEntity entity);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<RoleVO> toPage(Page<RoleEntity> page);

    RoleListQuery toListQuery(RolePageQuery query);

    RoleEntity toEntity(RoleAddRequest request);

    default RoleEntity updateConvert(RoleEntity entity, RoleUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (StrUtil.isNotBlank(request.getPermission())) {
            entity.setPermission(request.getPermission());
        }
        if (Objects.nonNull(request.getStatus())) {
            entity.setStatus(request.getStatus());
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
