package cn.hengzq.orange.admin.module.system.file.common.exception;


import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;

/**
 * @author 程序员橙子
 */
public interface SystemFileErrorCode extends GlobalErrorCodeConstant {

    String PREFIX = "SystemFile.";


    String CONFIG_STORAGE_CANNOT_NULL_KEY = PREFIX + "0001";
    ErrorCode CONFIG_STORAGE_CANNOT_NULL = new ErrorCode(CONFIG_STORAGE_CANNOT_NULL_KEY, "配置存储器不能为空");

    String CONFIG_NAME_CANNOT_NULL_KEY = PREFIX + "0002";
    ErrorCode CONFIG_NAME_CANNOT_NULL = new ErrorCode(CONFIG_NAME_CANNOT_NULL_KEY, "配置名称不能为空");

}
