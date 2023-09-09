package com.hzqing.orange.admin.starter.security.constants;

/**
 * @author 程序员橙子
 */
public interface SecurityConstants {

    String SERVICE_NAME = "orange-starter-security";

    /**
     * Token Key
     */
    String TOKEN = "token";


    /**
     * token 过期时间
     */
    long TOKEN_EXPIRE_TIME = 12 * 3600 * 1000;

    /**
     * 发行人
     */
    String ISSUER = "hengzq";


    /**
     * 用户ID
     */
    String PAYLOAD_KEY_ID = "id";

    /**
     * 租户id
     */
    String PAYLOAD_KEY_TENANT_ID = "tenantId";

    /**
     * 用户登陆账号
     */
    String PAYLOAD_KEY_USERNAME = "username";

    /**
     * 秘钥key
     */
    String DEFAULT_SECRET_KEY = "Orange-Cloud*2023.";

}