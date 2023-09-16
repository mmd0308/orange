package com.hzqing.orange.admin.module.system.permission.biz.converter;

import com.hzqing.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTreeVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Mapper
public interface DepartmentConverter extends Converter {

    DepartmentConverter INSTANCE = Mappers.getMapper(DepartmentConverter.class);

    DepartmentEntity toEntity(DepartmentVO departmentVO);

    DepartmentEntity toEntity(DepartmentAddRequest request);

    DepartmentVO toVo(DepartmentEntity entity);

    List<DepartmentTreeVO> toListTreeVo(List<DepartmentEntity> departmentEntityList);

    List<DepartmentVO> toList(List<DepartmentEntity> entityList);

    default DepartmentEntity updateConvert(DepartmentUpdateRequest request, DepartmentEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        entity.setParentId(request.getParentId());
        entity.setName(request.getName());
        entity.setSort(request.getSort());
        return entity;
    }

    DepartmentListQuery toListQuery(DepartmentTreeQuery query);

    DepartmentListQuery toListQuery(DepartmentAllQuery query);

}
