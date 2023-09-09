package com.hzqing.orange.admin.module.system.permission.biz.manager;

import  com.hzqing.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;


/**
 *@author 程序员橙子
 */
public interface DepartmentManager extends BaseManager<DepartmentEntity> {

    /**
     * 根据父级ID查询子集数据
     *
     * @param parentId
     * @return
     */
    List<DepartmentEntity> listByParentId(Long parentId);

    List<DepartmentEntity> listByParams(DepartmentListQuery queryDTO);

}
