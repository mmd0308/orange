package cn.hengzq.orange.admin.starter.common.constants.enums.support;


import cn.hengzq.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * 通用数据操作状态
 *
 * @author 程序员橙子
 */

public enum CommonOperationStatusEnum implements BaseEnum<Integer> {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    FAIL(1, "失败");

    private final Integer code;
    private final String msg;

    CommonOperationStatusEnum(Integer code, String msg) {
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
