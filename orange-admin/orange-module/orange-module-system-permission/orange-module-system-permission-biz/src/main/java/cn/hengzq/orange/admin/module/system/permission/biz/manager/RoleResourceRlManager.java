package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleResourceRlListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;

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
