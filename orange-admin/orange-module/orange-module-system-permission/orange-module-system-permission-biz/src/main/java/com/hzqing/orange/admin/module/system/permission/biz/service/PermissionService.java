package com.hzqing.orange.admin.module.system.permission.biz.service;

import com.hzqing.orange.admin.module.system.permission.common.vo.*;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface PermissionService {

    List<Long> queryRoleIdByUserId(Long userId);

    Boolean allotUserRole(AllotUserRole allotUserRole);

    Boolean allotRoleResource(AllotRoleResource allotUserRoleVo);

    ResourceIds queryResourceIdsByRoleId(Long roleId);


    /**
     * 查询当前
     *
     * @return
     */
    List<RouterTreeVO> queryCurrentUserRoutersTree();

    /**
     * 查询当前资源的权限标识
     *
     * @param userId 用户ID
     * @return 返回权限编码
     */
    ResourcePermissions queryAllPermissionsByUserId(Long userId);


}
