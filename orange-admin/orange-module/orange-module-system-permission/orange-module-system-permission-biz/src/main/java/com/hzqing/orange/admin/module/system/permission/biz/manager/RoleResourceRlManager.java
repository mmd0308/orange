package com.hzqing.orange.admin.module.system.permission.biz.manager;

import com.hzqing.orange.admin.module.system.permission.biz.dto.RoleResourceRlListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface RoleResourceRlManager extends BaseManager<RoleResourceRlEntity> {

    List<RoleResourceRlEntity> listByParams(RoleResourceRlListQuery query);


    List<RoleResourceRlEntity> listByRoleIds(List<Long> roleIds);

    void removeByRoleId(Long roleId);

    void batchAdd(List<RoleResourceRlEntity> roleResourceRlEntityList);
}
