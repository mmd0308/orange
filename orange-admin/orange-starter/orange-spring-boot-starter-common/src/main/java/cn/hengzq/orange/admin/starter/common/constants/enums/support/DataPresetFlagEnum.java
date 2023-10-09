package cn.hengzq.orange.admin.starter.common.constants.enums.support;


import cn.hengzq.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * sys_common_data_preset_flag
 * 预设标记 自定义数据或预置数据
 *
 * @author 程序员橙子
 */
public enum DataPresetFlagEnum implements BaseEnum<Integer> {

    /**
     * 预置数据
     */
    PRESET(0, "预置数据"),

    /**
     * 自定义数据
     */
    CUSTOM(1, "自定义数据");

    private final Integer code;
    private final String msg;

    DataPresetFlagEnum(Integer code, String msg) {
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
