package com.hzqing.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hzqing.orange.admin.module.system.permission.biz.dto.RoleResourceRlListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.RoleResourceRlManager;
import  com.hzqing.orange.admin.module.system.permission.biz.mapper.RoleResourceRlMapper;
import com.hzqing.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *@author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class RoleResourceRlManagerImpl extends BaseManagerImpl<RoleResourceRlMapper, RoleResourceRlEntity> implements RoleResourceRlManager {

    private final RoleResourceRlMapper mapper;

    @Override
    public RoleResourceRlMapper getMapper() {
        return this.mapper;
    }

    @Override
    public List<RoleResourceRlEntity> listByParams(RoleResourceRlListQuery query) {
        if (Objects.isNull(query)) {
            return List.of();
        }
        return mapper.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery()
                .in(CollUtil.isNotEmpty(query.getRoleIds()), RoleResourceRlEntity::getRoleId, query.getRoleIds())
                .eq(Objects.nonNull(query.getResourceType()), RoleResourceRlEntity::getResourceType, query.getResourceType())
                .eq(Objects.nonNull(query.getRoleId()), RoleResourceRlEntity::getRoleId, query.getRoleId())
        );
    }

    @Override
    public List<RoleResourceRlEntity> listByRoleIds(List<Long> roleIds) {
        if (CollUtil.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        return mapper.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery()
                .in(RoleResourceRlEntity::getRoleId, roleIds));
    }

    @Override
    public void removeByRoleId(Long roleId) {
        mapper.removeByRoleId(roleId);
    }

    @Override
    public void batchAdd(List<RoleResourceRlEntity> roleResourceRlEntityList) {
        mapper.batchInsert(roleResourceRlEntityList);
    }
}
