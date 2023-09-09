package com.hzqing.orange.admin.module.system.permission.biz.manager;

import  com.hzqing.orange.admin.module.system.permission.biz.entity.UserRoleRlEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface UserRoleRlManager extends BaseManager<UserRoleRlEntity> {

    /**
     * 根据用户ID查询用户和角色关系
     *
     * @param userId 用户ID
     * @return
     */
    List<UserRoleRlEntity> listByUserId(Long userId);

    void removeByUserId(Long userId);

    void batchAdd(List<UserRoleRlEntity> entityList);
}
