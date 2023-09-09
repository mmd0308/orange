package com.hzqing.orange.admin.module.system.permission.common.constants.exception;

import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;

/**
 * @author 程序员橙子
 */
public interface DepartmentErrorCode extends GlobalErrorCodeConstants {
    String PREFIX = "SystemPermissionDepartment.";

    String DEPARTMENT_DELETE_ERROR_EXIST_SUBSET_KEY = PREFIX + "0001";
    ErrorCode DEPARTMENT_DELETE_ERROR_EXIST_SUBSET = new ErrorCode(DEPARTMENT_DELETE_ERROR_EXIST_SUBSET_KEY, "存在子集部门，不允许删除");

    String DEPARTMENT_PARENT_DATA_NONEXISTENCE_KEY = PREFIX + "0002";
    ErrorCode DEPARTMENT_PARENT_DATA_NONEXISTENCE = new ErrorCode(DEPARTMENT_PARENT_DATA_NONEXISTENCE_KEY, "该父级数据不存在,请检查数据");
}
