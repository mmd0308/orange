package com.hzqing.orange.admin.module.system.permission.common.constants.exception;

import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;

/**
 * @author 程序员橙子
 */
public interface RoleErrorCode extends GlobalErrorCodeConstants {

    String PREFIX = "SystemPermissionRole.";

    String ROLE_PERMISSION_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode ROLE_PERMISSION_CANNOT_NULL = new ErrorCode(ROLE_PERMISSION_CANNOT_NULL_KEY, "角色权限编码不能为空");

    String ROLE_PERMISSION_CANNOT_REPEAT_KEY = PREFIX + "0002";
    ErrorCode ROLE_PERMISSION_CANNOT_REPEAT = new ErrorCode(ROLE_PERMISSION_CANNOT_REPEAT_KEY, "角色权限编码不能重复");

    String ROLE_NAME_CANNOT_NULL_KEY = PREFIX + "0003";
    ErrorCode ROLE_NAME_CANNOT_NULL = new ErrorCode(ROLE_NAME_CANNOT_NULL_KEY, "角色名称不能为空");


}
