package com.hzqing.orange.admin.module.system.permission.biz.service;

import com.hzqing.orange.admin.module.system.permission.common.vo.Department;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface DepartmentService {

    /**
     * 查询树型结构数据
     *
     * @param query
     * @return
     */
    List<DepartmentTree> queryTree(DepartmentTreeQuery query);

    /**
     * 根据ID删除
     *
     * @param id 主键
     * @return true:成功  false: 失败
     */
    Boolean removeById(Long id);

    /**
     * 新增数据
     *
     * @param department 新增数据
     * @return 新增数据主键
     */
    Long add(Department department);

    /**
     * 根据ID 查询自身及子集部门数据
     *
     * @param id
     * @return 返回本部门和子集部门数据
     */
    List<Department> querySelfAndSubsetById(Long id);

    Boolean updateById(Long id, DepartmentUpdateRequest request);

}
