package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import  com.hzqing.orange.admin.module.system.permission.biz.converter.DepartmentConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import  com.hzqing.orange.admin.module.system.permission.biz.service.DepartmentService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.constants.exception.DepartmentErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.Department;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;
import com.hzqing.orange.admin.starter.common.constants.CommonConstants;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentManager departmentManager;


    @Override
    public List<DepartmentTree> queryTree(DepartmentTreeQuery query) {
        DepartmentListQuery listQuery = DepartmentConverter.INSTANCE.toListQuery(query);
        List<DepartmentEntity> entityList = departmentManager.listByParams(listQuery);
        if (CollUtil.isEmpty(entityList)) {
            log.info("entityList is empty.");
            return List.of();
        }
        List<DepartmentTree> treeVoList = DepartmentConverter.INSTANCE.toListTreeVo(entityList);
        Map<Long, List<DepartmentTree>> departmentMap = treeVoList.stream().collect(Collectors.groupingBy(DepartmentTree::getParentId));
        List<Long> deleteSubIds = new ArrayList<>();
        // 组装子集
        treeVoList.forEach(item -> {
            List<DepartmentTree> subList = departmentMap.get(item.getId());
            if (CollUtil.isNotEmpty(subList)) {
                deleteSubIds.addAll(CollUtils.convertList(subList, DepartmentTree::getId));
            }
            item.setChildren(subList);
        });
        // 过滤掉子集数据
        return treeVoList.stream().filter(item -> !deleteSubIds.contains(item.getId())).collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long id) {
        Assert.nonNull(id);
        DepartmentEntity entity = departmentManager.getById(id);
        Assert.nonNull(entity, SystemPermissionErrorCode.GLOBAL_DATA_NOT_EXIST);
        List<DepartmentEntity> departmentEntityList = departmentManager.listByParentId(entity.getId());
        if (CollUtil.isNotEmpty(departmentEntityList)) {
            throw new ServiceException(DepartmentErrorCode.DEPARTMENT_DELETE_ERROR_EXIST_SUBSET);
        }
        departmentManager.removeById(id);
        return Boolean.TRUE;
    }

    @Override
    public Long add(Department department) {
        DepartmentEntity entity = DepartmentConverter.INSTANCE.toEntity(department);
        if (CommonConstants.Common.DEFAULT_PARENT_ID.equals(department.getParentId())) {
            entity.setAncestors(String.valueOf(CommonConstants.Common.DEFAULT_PARENT_ID));
        } else {
            DepartmentEntity parentEntity = departmentManager.getById(department.getParentId());
            Assert.nonNull(parentEntity, DepartmentErrorCode.DEPARTMENT_PARENT_DATA_NONEXISTENCE);
            entity.setAncestors(parentEntity.getAncestors() + StrUtil.UNDERLINE + parentEntity.getId());
        }
        return departmentManager.add(entity);
    }

    @Override
    public List<Department> querySelfAndSubsetById(Long id) {
        DepartmentEntity entity = departmentManager.getById(id);
        if (Objects.isNull(entity)) {
            log.warn("entity is null. id :{}", id);
            return List.of();
        }
        DepartmentListQuery listQuery = DepartmentListQuery.builder()
                .ancestorsLikeRight(entity.getAncestors() + StrUtil.UNDERLINE + entity.getId())
                .build();
        List<DepartmentEntity> entityList = departmentManager.listByParams(listQuery);
        entityList.add(entity);
        return DepartmentConverter.INSTANCE.toList(entityList);
    }

    @Override
    public Boolean updateById(Long id, DepartmentUpdateRequest request) {
        DepartmentEntity entity = departmentManager.getById(id);
        Assert.nonNull(entity, DepartmentErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity = DepartmentConverter.INSTANCE.updateConvert(request, entity);
        return departmentManager.updateById(entity);
    }
}
