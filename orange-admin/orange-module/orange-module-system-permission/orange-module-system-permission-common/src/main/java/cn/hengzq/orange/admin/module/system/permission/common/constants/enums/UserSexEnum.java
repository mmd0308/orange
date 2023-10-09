package cn.hengzq.orange.admin.module.system.permission.common.constants.enums;


import cn.hengzq.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * 用户性别
 *
 * @author 程序员橙子
 */
public enum UserSexEnum implements BaseEnum<Integer> {
    /**
     * 男
     */
    MALE(1, "男"),

    /**
     * 女
     */
    FEMALE(2, "女"),

    /**
     * 未知
     */
    UNKNOWN(3, "未知");

    private final Integer code;
    private final String msg;

    UserSexEnum(Integer code, String msg) {
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
