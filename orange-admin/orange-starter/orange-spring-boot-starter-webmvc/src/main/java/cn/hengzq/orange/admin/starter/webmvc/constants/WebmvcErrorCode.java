package cn.hengzq.orange.admin.starter.webmvc.constants;


import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;

public interface WebmvcErrorCode extends GlobalErrorCodeConstant {
    String PREFIX = "Webmvc.";

    ErrorCode WEBMVC_HTTP_REQUEST_METHOD_NOT_SUPPORTED = new ErrorCode(PREFIX + "0001", "不支持该请求，请检查URL是否正确");
}
