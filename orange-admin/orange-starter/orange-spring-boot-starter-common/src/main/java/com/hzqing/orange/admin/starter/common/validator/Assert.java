package com.hzqing.orange.admin.starter.common.validator;

import cn.hutool.core.collection.CollUtil;
import com.hzqing.orange.admin.starter.common.exception.ErrorCode;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import com.hzqing.orange.admin.starter.common.exception.ServiceException;

import java.util.Collection;
import java.util.Objects;

/**
 * 断言
 *
 * @author 程序员橙子
 */
public class Assert {

    /**
     * 检查对象不为空判断 如果为空 抛出参数缺失异常
     *
     * @param object 参数
     */
    public static void nonNull(Object object) {
        nonNull(object, GlobalErrorCodeConstants.GLOBAL_PARAMETER_CANNOT_NULL);
    }

    /**
     * 检查对象不为空判断 如果为空 抛出指定异常
     *
     * @param object    参数
     * @param errorCode 错误码
     */
    public static void nonNull(Object object, ErrorCode errorCode) {
        if (Objects.isNull(object)) {
            throw new ServiceException(errorCode == null ? GlobalErrorCodeConstants.GLOBAL_PARAMETER_CANNOT_NULL : errorCode);
        }
    }


    /**
     * 检查集合不为空
     * 如果为空，抛出异常
     *
     * @param collection 检查对象
     * @param errorCode  错误码
     */
    public static void notEmpty(Collection collection, ErrorCode errorCode) {
        if (CollUtil.isEmpty(collection)) {
            throw new ServiceException(Objects.isNull(errorCode) ? GlobalErrorCodeConstants.GLOBAL_PARAMETER_CANNOT_NULL : errorCode);
        }
    }

    /**
     * 检查集合为空
     * 如果不为空，抛出异常
     *
     * @param collection 检查对象
     * @param errorCode  错误码
     */
    public static void isEmpty(Collection collection, ErrorCode errorCode) {
        if (CollUtil.isNotEmpty(collection)) {
            throw new ServiceException(Objects.isNull(errorCode) ? GlobalErrorCodeConstants.GLOBAL_PARAMETER_IS_NULL : errorCode);
        }
    }


    /**
     * 检查对象为空判断 如果不为空 抛出异常
     *
     * @param object 参数
     */
    public static void isNull(Object object) {
        isNull(object, GlobalErrorCodeConstants.GLOBAL_REQUEST_MISSING_PARAMETER);
    }

    /**
     * 检查对象为空判断 如果不为空 抛出异常
     *
     * @param object    参数
     * @param errorCode 错误码
     */
    public static void isNull(Object object, ErrorCode errorCode) {
        if (Objects.nonNull(object)) {
            throw new ServiceException(errorCode == null ? GlobalErrorCodeConstants.GLOBAL_PARAMETER_IS_NULL : errorCode);
        }
    }


    /**
     * 检查对象为true 不为true 抛出异常
     *
     * @param bool
     * @param errorCode
     */
    public static void isTrue(boolean bool, ErrorCode errorCode) {
        if (!bool) {
            throw new ServiceException(errorCode == null ? GlobalErrorCodeConstants.GLOBAL_PARAMETER_IS_FALSE : errorCode);
        }
    }
}
