package cn.hengzq.orange.admin.starter.webmvc.interceptor;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hengzq.orange.admin.starter.common.constants.RequestConstants;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * 服务安全拦截器
 *
 * @author 程序员橙子
 */
@Slf4j
public class SecurityContextInterceptor implements AsyncHandlerInterceptor {

    private final AntPathMatcher pathMatcher;

    public SecurityContextInterceptor() {
        log.info("orange-starter-web init {} complete.", this.getClass().getSimpleName());
        this.pathMatcher = new AntPathMatcher();
    }

    private static final List<String> URL_WHITE_LIST = List.of("/**/auth/login",
            "/**/v3/api-docs", "/**/favicon.ico", "/*/swagger-ui**", "/*/swagger-ui/**", "/**/doc.html",
            "/**/password-encrypt",
            "/**/webjars/**", "/**/v3/api-docs/**",
            "/rest/orange/basics/v1.0/file/download/*/get/**",
            "/rest/orange/basics/v1.0/file/preview/*/get/**");

    @Override
    @SuppressWarnings("NullableProblems")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String path = request.getRequestURI().toLowerCase(Locale.ROOT);
        // 白名单直接放行
        if (ignoreUrl(path)) {
            log.info("white list url:{}", path);
            return true;
        }

        String userId = request.getHeader(RequestConstants.InnerHeader.ORANGE_INNER_USER_ID);
        String tenantId = request.getHeader(RequestConstants.InnerHeader.ORANGE_INNER_TENANT_ID);
        if (StrUtil.isBlank(tenantId)) {
            log.warn("request header miss orange-inner-tenant-id url:{}", path);
            return false;
        }
        GlobalContextHelper.setContext(Long.parseLong(tenantId),
                Objects.isNull(userId) ? null : Long.parseLong(userId));
        return true;
    }

    private boolean ignoreUrl(String path) {
        if (CollUtil.isNotEmpty(URL_WHITE_LIST)) {
            for (String pattern : URL_WHITE_LIST) {
                if (pathMatcher.match(pattern, path)) {
                    return true;
                }
            }
        }
        return false;
    }
}
