package com.hzqing.orange.admin.module.system.permission.common.constants.enums;


import com.hzqing.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * 登陆日志操作类型
 *
 * @author 程序员橙子
 */
public enum LoginRecordTypeEnum implements BaseEnum<Integer> {
    /**
     * 登陆
     */
    LOGIN(0, "登录"),
    /**
     * 登出
     */
    LOGOUT(1, "登出");


    private final Integer code;
    private final String msg;

    LoginRecordTypeEnum(Integer code, String msg) {
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
