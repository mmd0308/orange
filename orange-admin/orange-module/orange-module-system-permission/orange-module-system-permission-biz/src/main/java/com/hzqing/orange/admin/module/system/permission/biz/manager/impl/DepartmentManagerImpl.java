package com.hzqing.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import  com.hzqing.orange.admin.module.system.permission.biz.mapper.DepartmentMapper;
import com.hzqing.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 *@author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class DepartmentManagerImpl extends BaseManagerImpl<DepartmentMapper, DepartmentEntity> implements DepartmentManager {

    private final DepartmentMapper mapper;

    @Override
    public DepartmentMapper getMapper() {
        return this.mapper;
    }


    @Override
    public List<DepartmentEntity> listByParentId(Long parentId) {
        return mapper.selectByParentId(parentId);
    }

    @Override
    public List<DepartmentEntity> listByParams(DepartmentListQuery query) {
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = Wrappers.<DepartmentEntity>lambdaQuery()
                .eq(Objects.nonNull(query.getParentId()), DepartmentEntity::getParentId, query.getParentId())
                .eq(StrUtil.isNotBlank(query.getName()), DepartmentEntity::getName, query.getName())
                .like(StrUtil.isNotBlank(query.getNameLike()), DepartmentEntity::getName, query.getNameLike())
                .likeRight(StrUtil.isNotBlank(query.getAncestorsLikeRight()), DepartmentEntity::getAncestors, query.getAncestorsLikeRight())
                .in(CollUtil.isNotEmpty(query.getIds()), DepartmentEntity::getId, query.getIds())
                .orderByDesc(DepartmentEntity::getSort);
        return mapper.selectList(queryWrapper);
    }
}
