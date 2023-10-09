package cn.hengzq.orange.admin.starter.common.constants.enums.support;


import cn.hengzq.orange.admin.starter.common.constants.enums.BaseEnum;

/**
 * 请求方式
 *
 * @author 程序员橙子
 */
public enum RequestMethodEnum implements BaseEnum<String> {

    /**
     * GET
     */
    GET,

    /**
     * POST
     */
    POST,

    /**
     * PUT
     */
    PUT,

    /**
     * PATCH
     */
    PATCH,

    /**
     * DELETE
     */
    DELETE;

    @Override
    public String getCode() {
        return this.name();
    }

}
