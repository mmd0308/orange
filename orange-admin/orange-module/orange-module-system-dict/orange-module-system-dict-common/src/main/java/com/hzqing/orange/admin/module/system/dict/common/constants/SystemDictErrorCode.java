package com.hzqing.orange.admin.module.system.dict.common.constants;


import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;

/**
 * @author 程序员橙子
 */
public interface SystemDictErrorCode extends GlobalErrorCodeConstants {

    String PREFIX = "SystemDict.";


    String DICT_TYPE_NAME_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode DICT_TYPE_NAME_CANNOT_NULL = new ErrorCode(DICT_TYPE_NAME_CANNOT_NULL_KEY, "字典名称不能为空");

    String DICT_TYPE_TYPE_CANNOT_NULL_KEY = PREFIX + "0002";
    ErrorCode DICT_TYPE_TYPE_CANNOT_NULL = new ErrorCode(DICT_TYPE_NAME_CANNOT_NULL_KEY, "字典类型不能为空");

    String DICT_TYPE_TYPE_ALREADY_EXIST_KEY = PREFIX + "0003";
    ErrorCode DICT_TYPE_TYPE_ALREADY_EXIST = new ErrorCode(DICT_TYPE_TYPE_ALREADY_EXIST_KEY, "字典类型已存在");

    String DICT_TYPE_DELETE_ERROR_EXIST_DATA_KEY = PREFIX + "0004";
    ErrorCode DICT_TYPE_DELETE_ERROR_EXIST_DATA = new ErrorCode(DICT_TYPE_DELETE_ERROR_EXIST_DATA_KEY, "存在关联字典数据，删除失败");

}
