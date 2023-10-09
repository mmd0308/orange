package cn.hengzq.orange.admin.starter.webmvc.exception;

import cn.hutool.core.util.StrUtil;
import cn.hengzq.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.message.GlobalMessageSource;
import cn.hengzq.orange.admin.starter.common.message.LocalMessage;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.webmvc.constants.WebmvcErrorCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 基础异常统一处理
 *
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * 自定义参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Object> handle(MethodArgumentNotValidException e) {
        //获取参数校验错误集合
        List<FieldError> fieldErrors = e.getFieldErrors();
        log.warn("the request parameter verification failed. fieldErrors:{}", fieldErrors);
        //给用户提供友好的错误提示
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("[%s]个参数校验错误: ", fieldErrors.size()));
        for (FieldError error : fieldErrors) {
            sb.append("[").append(error.getField()).append("]");
            if (StrUtil.isNotBlank(error.getDefaultMessage())) {
                String msg = LocalMessage.getErrMsg(error.getDefaultMessage());
                if (StrUtil.isBlank(msg)) {
                    msg = GlobalMessageSource.getAccessor().getMessage(error.getDefaultMessage(), null, "参数错误");
                }
                sb.append(msg);
            }
            sb.append(";");
        }
        Result<Object> result = ResultWrapper.fail();
        result.setMsg(sb.toString());
        return result;
    }

    /**
     * 自定义服务异常处理
     *
     */
    @ExceptionHandler({ServiceException.class,})
    public Result<?> handleServiceException(ServiceException e) {
        log.error("handleServiceException, code:{} msg:{}", e.getErrorCode().getCode(), e.getErrorCode().getMsg());
        return ResultWrapper.fail(e.getErrorCode());
    }

    /**
     * 不支持接口异常处理
     *
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,})
    public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("HttpRequestMethodNotSupportedException msg:{}", e.getMessage());
        return ResultWrapper.fail(WebmvcErrorCode.WEBMVC_HTTP_REQUEST_METHOD_NOT_SUPPORTED);
    }

    /**
     * 枚举值匹配异常
     *
     */
    @ExceptionHandler({HttpMessageNotReadableException.class,})
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("handleHttpMessageNotReadableException msg:{}", e.getMessage());
        return ResultWrapper.fail(WebmvcErrorCode.GLOBAL_REQUEST_PARAMETER_CHECK_ERROR);
    }

    /**
     * security 异常处理
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result<?> handleException(AccessDeniedException e) {
        log.error("handleException msg:{} ", e.getMessage(), e);
        return ResultWrapper.fail(GlobalErrorCodeConstants.GLOBAL_FORBIDDEN);
    }

    /**
     * 最大的异常捕获
     */
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        log.error("handleException msg:{} ", e.getMessage(), e);
        return ResultWrapper.fail();
    }

}
