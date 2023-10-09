package cn.hengzq.orange.admin.starter.common.constants.enums;

import java.io.Serializable;

/**
 * 基础枚举
 *
 * @author 程序员橙子
 */

public interface BaseEnum<T extends Serializable> {

    /**
     * 获取code编码
     *
     * @return 返回code编码
     */
    T getCode();

}
