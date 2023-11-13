package cn.hengzq.starter.storage.constant;


import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;

/**
 * @author 程序员橙子
 */
public interface StorageErrorCode extends GlobalErrorCodeConstant {
    ErrorCode FILE_UPLOAD_FAILED = new ErrorCode("10010000L", "文件上传失败.");
}