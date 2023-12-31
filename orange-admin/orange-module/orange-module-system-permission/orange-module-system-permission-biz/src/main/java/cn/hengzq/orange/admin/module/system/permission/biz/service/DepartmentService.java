package cn.hengzq.orange.admin.module.system.permission.biz.service;

import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.DepartmentAddOrUpdateRequest;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface DepartmentService {

    /**
     * 查询树型结构数据
     *
     * @param query
     * @return
     */
    List<DepartmentTreeVO> queryTree(DepartmentTreeQuery query);

    /**
     * 根据ID删除
     *
     * @param id 主键
     * @return true:成功  false: 失败
     */
    Boolean removeById(Long id);

    /**
     * 新增数据
     */
    Long add(DepartmentAddOrUpdateRequest request);

    /**
     * 根据ID 查询自身及子集部门数据
     */
    List<DepartmentVO> querySelfAndSubsetById(Long id);

    Boolean updateById(Long id, DepartmentAddOrUpdateRequest request);

    List<DepartmentVO> queryAll(DepartmentAllQuery query);
}
