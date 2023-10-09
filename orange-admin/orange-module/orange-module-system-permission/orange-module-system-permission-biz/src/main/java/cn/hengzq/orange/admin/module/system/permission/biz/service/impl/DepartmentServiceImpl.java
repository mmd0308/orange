package cn.hengzq.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hengzq.orange.admin.module.system.permission.biz.converter.DepartmentConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.DepartmentService;
import cn.hengzq.orange.admin.module.system.permission.common.exception.support.DepartmentErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.DepartmentAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.CommonConstant;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.common.util.Assert;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentManager departmentManager;


    @Override
    public List<DepartmentTreeVO> queryTree(DepartmentTreeQuery query) {
        DepartmentListQuery listQuery = DepartmentConverter.INSTANCE.toListQuery(query);
        List<DepartmentEntity> entityList = departmentManager.listByParams(listQuery);
        if (CollUtil.isEmpty(entityList)) {
            log.info("entityList is empty.");
            return List.of();
        }
        List<DepartmentTreeVO> treeVoList = DepartmentConverter.INSTANCE.toListTreeVo(entityList);
        Map<Long, List<DepartmentTreeVO>> departmentMap = treeVoList.stream().collect(Collectors.groupingBy(DepartmentTreeVO::getParentId));
        List<Long> deleteSubIds = new ArrayList<>();
        // 组装子集
        treeVoList.forEach(item -> {
            List<DepartmentTreeVO> subList = departmentMap.get(item.getId());
            if (CollUtil.isNotEmpty(subList)) {
                deleteSubIds.addAll(CollUtils.convertList(subList, DepartmentTreeVO::getId));
            }
            item.setChildren(subList);
        });
        // 过滤掉子集数据
        return treeVoList.stream().filter(item -> !deleteSubIds.contains(item.getId())).collect(Collectors.toList());
    }

    @Override
    public Boolean removeById(Long id) {
        DepartmentEntity entity = departmentManager.getById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        List<DepartmentEntity> departmentEntityList = departmentManager.listByParentId(entity.getId());
        if (CollUtil.isNotEmpty(departmentEntityList)) {
            throw new ServiceException(DepartmentErrorCode.DEPARTMENT_DELETE_ERROR_EXIST_SUBSET);
        }
        departmentManager.removeById(id);
        return Boolean.TRUE;
    }

    @Override
    public Long add(DepartmentAddRequest request) {
        DepartmentEntity entity = DepartmentConverter.INSTANCE.toEntity(request);
        if (Objects.isNull(request.getParentId())) {
            entity.setParentId(CommonConstant.DEFAULT_PARENT_ID);
        }
        return departmentManager.add(entity);
    }

    @Override
    public List<DepartmentVO> querySelfAndSubsetById(Long id) {
        DepartmentEntity entity = departmentManager.getById(id);
        if (Objects.isNull(entity)) {
            log.warn("entity is null. id:{}", id);
            return List.of();
        }
        List<DepartmentEntity> entityList = new ArrayList<>();
        entityList.add(entity);
        entityList.addAll(departmentManager.listSubsetByParentId(id));
        return DepartmentConverter.INSTANCE.toList(entityList);
    }


    @Override
    public Boolean updateById(Long id, DepartmentUpdateRequest request) {
        DepartmentEntity entity = departmentManager.getById(id);
        Assert.nonNull(entity, DepartmentErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity = DepartmentConverter.INSTANCE.updateConvert(request, entity);
        return departmentManager.updateById(entity);
    }

    @Override
    public List<DepartmentVO> queryAll(DepartmentAllQuery query) {
        DepartmentListQuery listQuery = DepartmentConverter.INSTANCE.toListQuery(query);
        List<DepartmentEntity> entityList = departmentManager.listByParams(listQuery);
        return DepartmentConverter.INSTANCE.toList(entityList);
    }
}
