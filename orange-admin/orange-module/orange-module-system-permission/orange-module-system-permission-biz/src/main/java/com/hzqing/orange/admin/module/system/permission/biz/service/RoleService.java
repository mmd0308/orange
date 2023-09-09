package com.hzqing.orange.admin.module.system.permission.biz.service;

import com.hzqing.orange.admin.module.system.permission.common.vo.Role;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.RolePageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleUpdateRequest;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface RoleService {

    /**
     * 根据用户ID，查询用户拥有的角色
     *
     * @param userId 用户ID
     * @return 用户拥有的角色
     */
    List<Role> queryByUserId(Long userId);

    /**
     * 分页查询
     *
     * @param query 查询条件
     * @return 返回满足条件的结果集
     */
    PageVO<Role> page(RolePageQuery query);

    /**
     * 新增角色
     *
     * @param request 新增角色参数
     * @return 新增数据ID
     */
    Long add(RoleAddRequest request);

    /**
     * 根据ID更新
     *
     * @param id      主键
     * @param request 更新数据
     * @return 返回结果
     */
    Boolean updateById(Long id, RoleUpdateRequest request);


}
