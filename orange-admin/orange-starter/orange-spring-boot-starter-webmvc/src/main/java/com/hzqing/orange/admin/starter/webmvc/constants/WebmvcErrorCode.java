package com.hzqing.orange.admin.starter.webmvc.constants;


import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;

public interface WebmvcErrorCode extends GlobalErrorCodeConstants {
    String PREFIX = "Webmvc.";

    ErrorCode WEBMVC_HTTP_REQUEST_METHOD_NOT_SUPPORTED = new ErrorCode(PREFIX + "0001", "不支持该请求，请检查URL是否正确");
}
