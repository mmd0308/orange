package cn.hengzq.orange.admin.module.system.permission.common.constants.enums;


import cn.hengzq.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * @author 程序员橙子
 */
public enum ResourceTypeEnum implements BaseEnum<Integer> {
    /**
     * 菜单
     */
    MENU(1, "菜单"),

    /**
     * 按钮
     */
    BUTTON(2, "按钮");

    private final Integer code;
    private final String msg;

    ResourceTypeEnum(Integer code, String msg) {
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
