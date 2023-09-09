package com.hzqing.orange.admin.starter.security.filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.Claim;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import com.hzqing.orange.admin.starter.common.exception.ServiceException;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
import com.hzqing.orange.admin.starter.security.constants.SecurityConstants;
import com.hzqing.orange.admin.starter.security.dto.LoginUser;
import com.hzqing.orange.admin.starter.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class JWTTokenAuthenticationFilter extends OncePerRequestFilter {

    public JWTTokenAuthenticationFilter() {
        if (log.isDebugEnabled()) {
            log.debug("{} init JWTTokenAuthenticationFilter complete.", SecurityConstants.SERVICE_NAME);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("{} URL:{}", SecurityConstants.SERVICE_NAME, request.getRequestURL());
        }
        // 获取请求携带的令牌
        String token = getToken(request);
        // 没有Token直接放行，交给Spring Security处理
        if (StrUtil.isNotBlank(token)) {
            Map<String, Claim> claimMap = JWTUtil.parseToken(token);
            if (Objects.isNull(claimMap) || claimMap.isEmpty() || !JWTUtil.isTokenValid(token)) {
                log.error("token is illegal.");
                throw new ServiceException(GlobalErrorCodeConstants.GLOBAL_INTERNAL_SERVER_ERROR);
            }
            LoginUser loginUser = buildLoginUser(claimMap);
            // 重新将用户信息封装到UsernamePasswordAuthenticationToken
            Authentication authentication = buildAuthentication(loginUser, request);
            // 将信息存入上下文对象
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 设置全局上下文
            GlobalContextHelper.setContext(loginUser.getTenantId(), loginUser.getId(), loginUser.getUsername());
        }

        chain.doFilter(request, response);
    }

    /**
     * 构建UsernamePasswordAuthenticationToken
     *
     * @param loginUser 当前登录用户
     * @param request   HttpServletRequest
     * @return
     */
    private static Authentication buildAuthentication(LoginUser loginUser, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser, null, Collections.emptyList());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }

    /**
     * 构建登录用户信息
     *
     * @param claimMap
     * @return
     */
    private LoginUser buildLoginUser(Map<String, Claim> claimMap) {
        return LoginUser.builder().id(claimMap.get(SecurityConstants.PAYLOAD_KEY_ID).asLong()).tenantId(claimMap.get(SecurityConstants.PAYLOAD_KEY_TENANT_ID).asLong()).username(claimMap.get(SecurityConstants.PAYLOAD_KEY_USERNAME).asString()).build();
    }

    /**
     * 从请求头中获取token
     *
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        if (Objects.nonNull(request.getHeader(SecurityConstants.TOKEN))) {
            return request.getHeader(SecurityConstants.TOKEN);
        }
        return null;
    }
}
