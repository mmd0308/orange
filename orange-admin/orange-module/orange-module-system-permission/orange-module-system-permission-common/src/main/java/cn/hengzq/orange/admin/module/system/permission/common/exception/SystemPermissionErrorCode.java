package cn.hengzq.orange.admin.module.system.permission.common.exception;


import cn.hengzq.orange.admin.module.system.permission.common.exception.support.DepartmentErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.exception.support.MenuErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.exception.support.UserErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;

/**
 * @author 程序员橙子
 */
public interface SystemPermissionErrorCode extends GlobalErrorCodeConstant,
        DepartmentErrorCode, UserErrorCode, MenuErrorCode {


}
