package cn.hengzq.orange.admin.starter.security.util;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import cn.hengzq.orange.admin.starter.security.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Slf4j
public class JWTUtil {

    public static void main(String[] args) {
        String token = createToken(-100L);
        System.out.println(token);
    }

    /**
     * 创建租户级别的token
     *
     * @param tenantId 租户ID
     * @return 返回token
     */
    public static String createToken(Long tenantId) {
        Date now = new Date();
        String token = JWT.create()
                .withIssuer(SecurityConstants.ISSUER)
                .withIssuedAt(now)
                .withClaim(SecurityConstants.PAYLOAD_KEY_TENANT_ID, tenantId)
                .sign(Algorithm.HMAC256(SecurityConstants.DEFAULT_SECRET_KEY));
        return token;
    }


    /**
     * 创建token
     *
     * @param userId       当前登录的用户ID
     * @param loginAccount 当前登录的用户账号
     * @param tenantId     当前登录用户所属租户
     * @return 返回token
     */
    public static String createToken(Long userId, String loginAccount, Long tenantId) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + SecurityConstants.TOKEN_EXPIRE_TIME);
        return JWT.create()
                .withIssuer(SecurityConstants.ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(expireTime)
                .withClaim(SecurityConstants.PAYLOAD_KEY_ID, userId)
                .withClaim(SecurityConstants.PAYLOAD_KEY_USERNAME, loginAccount)
                .withClaim(SecurityConstants.PAYLOAD_KEY_TENANT_ID, tenantId)
                .sign(Algorithm.HMAC256(SecurityConstants.DEFAULT_SECRET_KEY));
    }


    /**
     * 检查token是否非法
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SecurityConstants.DEFAULT_SECRET_KEY);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(SecurityConstants.ISSUER).build();
            jwtVerifier.verify(token);
        } catch (Exception ex) {
            log.error("Illegal token. token:{}", token);
            return false;
        }
        return true;
    }

    /**
     * 检查token是否有效
     *
     * @return 有效：True 无效：false
     */
    public static Boolean isTokenValid(String token) {
        if (StrUtil.isBlank(token)) {
            return Boolean.FALSE;
        }
        if (!verify(token)) {
            return Boolean.FALSE;
        }
        Map<String, Claim> claimMap = parseToken(token);

        if (Objects.isNull(claimMap) || claimMap.isEmpty()) {
            return Boolean.FALSE;
        }
        // token 中是否存在用户ID
        if (!claimMap.containsKey(SecurityConstants.PAYLOAD_KEY_ID)
                || Objects.isNull(claimMap.get(SecurityConstants.PAYLOAD_KEY_ID))) {
            return Boolean.FALSE;
        }
        // token 一定包含租户ID
        if (!claimMap.containsKey(SecurityConstants.PAYLOAD_KEY_TENANT_ID)
                || Objects.isNull(claimMap.get(SecurityConstants.PAYLOAD_KEY_TENANT_ID))) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 解析token 获取token中数据
     */
    public static Map<String, Claim> parseToken(String token) {
        try {
            return JWT.decode(token).getClaims();
        } catch (Exception e) {
            log.error("parseToken is error. token:{}", token);
        }
        return Map.of();
    }

}