package com.hzqing.orange.admin.module.system.permission.common.constants.exception;

import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;

/**
 * @author 程序员橙子
 */
public interface UserErrorCode extends GlobalErrorCodeConstants {

    String PREFIX = "SystemPermissionUser.";

    String USER_USERNAME_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode USER_USERNAME_CANNOT_NULL = new ErrorCode(USER_USERNAME_CANNOT_NULL_KEY, "用户账号不能为空");
}
