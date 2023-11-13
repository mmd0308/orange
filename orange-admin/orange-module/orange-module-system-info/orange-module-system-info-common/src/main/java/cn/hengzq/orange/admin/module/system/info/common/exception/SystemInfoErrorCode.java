package cn.hengzq.orange.admin.module.system.info.common.exception;


import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;

/**
 * @author 程序员橙子
 */
public interface SystemInfoErrorCode extends GlobalErrorCodeConstant {


    String TYPE_PREFIX = "SystemInfoType.";

    String TYPE_DELETE_ERROR_EXIST_SUBSET_KEY = TYPE_PREFIX + "0001";
    ErrorCode TYPE_DELETE_ERROR_EXIST_SUBSET = new ErrorCode(TYPE_DELETE_ERROR_EXIST_SUBSET_KEY, "存在子集类型，不允许删除");

    String TYPE_CODE_CANNOT_REPEAT_KEY = TYPE_PREFIX + "0002";

    ErrorCode TYPE_CODE_CANNOT_REPEAT = new ErrorCode(TYPE_CODE_CANNOT_REPEAT_KEY, "类型编码重复");


    String TYPE_CODE_CANNOT_NULL_KEY = TYPE_PREFIX + "0003";

    ErrorCode TYPE_CODE_CANNOT_NULL = new ErrorCode(TYPE_CODE_CANNOT_NULL_KEY, "类型不能为空");

}
