package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.DepartmentAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hutool.core.util.StrUtil;
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

    DepartmentEntity toEntity(DepartmentAddOrUpdateRequest request);

    DepartmentVO toVo(DepartmentEntity entity);

    List<DepartmentTreeVO> toListTreeVo(List<DepartmentEntity> departmentEntityList);

    List<DepartmentVO> toList(List<DepartmentEntity> entityList);

    default DepartmentEntity updateConvert(DepartmentEntity entity, DepartmentAddOrUpdateRequest request) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.nonNull(request.getParentId())) {
            entity.setParentId(request.getParentId());
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (Objects.nonNull((request.getSort()))) {
            entity.setSort(request.getSort());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }

    DepartmentListQuery toListQuery(DepartmentTreeQuery query);

    DepartmentListQuery toListQuery(DepartmentAllQuery query);

}
