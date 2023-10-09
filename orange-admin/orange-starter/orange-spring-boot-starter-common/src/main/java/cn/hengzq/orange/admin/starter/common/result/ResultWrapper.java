package cn.hengzq.orange.admin.starter.common.result;

import cn.hutool.core.util.StrUtil;
import cn.hengzq.orange.admin.starter.common.exception.ErrorCode;
import cn.hengzq.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import cn.hengzq.orange.admin.starter.common.message.GlobalMessageSource;
import cn.hengzq.orange.admin.starter.common.message.LocalMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * result 接口封装
 *
 * @author 程序员橙子
 */
@Slf4j
public class ResultWrapper {

    public static <T> Result<T> result() {
        Result<T> result = new Result<>();
        result.setRequestId(Thread.currentThread().getName());
        return result;
    }

    /**
     * 返回成功
     *
     * @param <T> 数据类型
     * @return 成功 空结果集
     */
    public static <T> Result<T> ok() {
        return result();
    }

    /**
     * 返回成功结果
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 封装结果集
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = ok();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail() {
        return fail(GlobalErrorCodeConstants.GLOBAL_REQUEST_FAILED);
    }

    public static <T> Result<T> fail(ErrorCode errorCode) {
        Result<T> result = result();
        String code = errorCode.getCode();
        result.setCode(code);
        String msg = LocalMessage.getErrMsg(code);
        if (StrUtil.isBlank(msg)) {
            msg = GlobalMessageSource.getAccessor().getMessage(code, null, "参数错误");
        }
        result.setMsg(StrUtil.isBlank(msg) ? errorCode.getMsg() : msg);
        return result;
    }
}
