package cn.hengzq.orange.admin.starter.common.constants;

/**
 * 请求常量
 *
 * @author 程序员橙子
 */
public interface RequestConstants {

    /**
     * 内部请求头Key
     */
    interface InnerHeader {
        /**
         * 请求头 租户ID Key
         */
        String ORANGE_INNER_TENANT_ID = "orange-inner-tenant-id";

        /**
         * 请求头 用户ID
         */
        String ORANGE_INNER_USER_ID = "orange-inner-user-id";

        /**
         * 请求头 requestId
         */
        String ORANGE_INNER_REQUEST_ID = "orange-inner-request-id";

    }

    /**
     * 响应请求头
     */
    interface ResponseHeader {
        /**
         * 请求头 requestId
         */
        String ORANGE_REQUEST_ID = "orange-request-id";


    }
}
