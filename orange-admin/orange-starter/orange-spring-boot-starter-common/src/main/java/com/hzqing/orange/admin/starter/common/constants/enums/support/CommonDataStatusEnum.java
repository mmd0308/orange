package com.hzqing.orange.admin.starter.common.constants.enums.support;


import com.hzqing.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * 数据状态
 *
 * @author 程序员橙子
 */

public enum CommonDataStatusEnum implements BaseEnum<Integer> {
    /**
     * 正常
     */
    NORMAL(0, "正常"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    private final Integer code;
    private final String msg;

    CommonDataStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return msg;
    }
}
