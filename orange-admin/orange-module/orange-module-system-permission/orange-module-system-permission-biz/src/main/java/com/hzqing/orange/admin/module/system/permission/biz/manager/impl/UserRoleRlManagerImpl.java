package com.hzqing.orange.admin.module.system.permission.biz.manager.impl;

import  com.hzqing.orange.admin.module.system.permission.biz.entity.UserRoleRlEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.UserRoleRlManager;
import  com.hzqing.orange.admin.module.system.permission.biz.mapper.UserRoleRlMapper;
import com.hzqing.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class UserRoleRlManagerImpl extends BaseManagerImpl<UserRoleRlMapper, UserRoleRlEntity> implements UserRoleRlManager {
    private final UserRoleRlMapper mapper;

    @Override
    public UserRoleRlMapper getMapper() {
        return this.mapper;
    }

    @Override
    public List<UserRoleRlEntity> listByUserId(Long userId) {
        return mapper.selectByUserId(userId);
    }

    @Override
    public void removeByUserId(Long userId) {
        mapper.removeByUserId(userId);
    }

    @Override
    public void batchAdd(List<UserRoleRlEntity> entityList) {
        mapper.batchInsert(entityList);
    }
}
