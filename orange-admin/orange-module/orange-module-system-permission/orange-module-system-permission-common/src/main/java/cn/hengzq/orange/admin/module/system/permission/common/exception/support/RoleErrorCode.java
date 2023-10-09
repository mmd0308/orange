package cn.hengzq.orange.admin.module.system.permission.common.exception.support;

import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;

/**
 * @author 程序员橙子
 */
public interface RoleErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemPermissionRole.";

    String ROLE_PERMISSION_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode ROLE_PERMISSION_CANNOT_NULL = new ErrorCode(ROLE_PERMISSION_CANNOT_NULL_KEY, "角色权限编码不能为空");

    String ROLE_PERMISSION_CANNOT_REPEAT_KEY = PREFIX + "0002";
    ErrorCode ROLE_PERMISSION_CANNOT_REPEAT = new ErrorCode(ROLE_PERMISSION_CANNOT_REPEAT_KEY, "角色权限编码不能重复");

    String ROLE_NAME_CANNOT_NULL_KEY = PREFIX + "0003";
    ErrorCode ROLE_NAME_CANNOT_NULL = new ErrorCode(ROLE_NAME_CANNOT_NULL_KEY, "角色名称不能为空");


}
