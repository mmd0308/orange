package cn.hengzq.orange.admin.starter.common.enums.support;


import cn.hengzq.orange.admin.starter.common.enums.BaseEnum;

/**
 * 数据启用状态
 *
 * @author 程序员橙子
 */

public enum CommonDataEnableStatusEnum implements BaseEnum<Integer> {
    /**
     * 启用
     */
    ENABLE(0, "启用"),
    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    private final Integer code;
    private final String msg;

    CommonDataEnableStatusEnum(Integer code, String msg) {
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
