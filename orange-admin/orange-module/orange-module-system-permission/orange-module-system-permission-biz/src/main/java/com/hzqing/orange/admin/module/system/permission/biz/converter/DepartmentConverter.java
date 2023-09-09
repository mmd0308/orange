package com.hzqing.orange.admin.module.system.permission.biz.converter;

import  com.hzqing.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import com.hzqing.orange.admin.module.system.permission.common.vo.Department;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 *@author 程序员橙子
 */
@Mapper
public interface DepartmentConverter extends Converter {

    DepartmentConverter INSTANCE = Mappers.getMapper(DepartmentConverter.class);

    DepartmentEntity toEntity(Department department);

    Department toVo(DepartmentEntity entity);

    List<DepartmentTree> toListTreeVo(List<DepartmentEntity> departmentEntityList);

    List<Department> toList(List<DepartmentEntity> entityList);

    default DepartmentEntity updateConvert(DepartmentUpdateRequest request, DepartmentEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }
        entity.setName(request.getName());
        entity.setSort(request.getSort());
        return entity;
    }

    DepartmentListQuery toListQuery(DepartmentTreeQuery query);
}
