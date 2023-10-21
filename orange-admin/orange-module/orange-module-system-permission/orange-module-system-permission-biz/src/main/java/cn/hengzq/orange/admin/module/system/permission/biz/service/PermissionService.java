package cn.hengzq.orange.admin.module.system.permission.biz.service;

import cn.hengzq.orange.admin.module.system.permission.common.vo.request.AllotUserRoleRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourceIds;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RouterTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.AllotRoleResourceRequest;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface PermissionService {

    Boolean allotUserRole(AllotUserRoleRequest allotUserRoleRequest);

    Boolean allotRoleResource(AllotRoleResourceRequest allotUserRoleVo);

    ResourceIds queryResourceIdsByRoleId(Long roleId);

    /**
     * 查询当前
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
