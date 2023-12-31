package cn.hengzq.orange.admin.module.system.permission.common.exception.support;

import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;

/**
 * @author 程序员橙子
 */
public interface ButtonErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemPermissionButton.";
    ErrorCode BUTTON_PERMISSION_CANNOT_NULL = new ErrorCode(PREFIX + "0001", "按钮权限编码不能为空");

    ErrorCode BUTTON_PERMISSION_CANNOT_REPEAT = new ErrorCode(PREFIX + "0002", "按钮权限编码重复");

}
