package cn.hengzq.orange.admin.module.system.permission.api;

import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourcePermissions;

/**
 * @author 程序员橙子
 */
public interface PermissionApi {
    ResourcePermissions queryAllPermissionsByUserId(Long userId);
}
