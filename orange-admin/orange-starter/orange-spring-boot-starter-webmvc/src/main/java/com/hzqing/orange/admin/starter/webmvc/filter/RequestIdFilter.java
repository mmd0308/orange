package com.hzqing.orange.admin.starter.webmvc.filter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.hzqing.orange.admin.starter.common.constants.RequestConstants;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
import com.hzqing.orange.admin.starter.webmvc.constants.WebmvcConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;

import java.io.IOException;

/**
 * @author 程序员橙子
 */
@Slf4j
public class RequestIdFilter extends HttpFilter implements Ordered {

    public RequestIdFilter() {
        if (log.isDebugEnabled()) {
            log.debug("{} init {} complete.", WebmvcConstants.SERVICE_NAME, this.getClass().getSimpleName());
        }
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        TimeInterval timer = DateUtil.timer();
        String traceId = request.getHeader(RequestConstants.InnerHeader.ORANGE_INNER_REQUEST_ID);
        traceId = StrUtil.isBlank(traceId) ? IdUtil.getSnowflakeNextIdStr() : traceId;
        log.info("request start traceId:{},URL:{}", traceId, request.getRequestURI());
        GlobalContextHelper.removeContext();
        GlobalContextHelper.setContext(traceId);
        chain.doFilter(request, response);
        log.info("request end, URL:{}，duration:{} ms", request.getRequestURI(), timer.intervalMs());
    }

    @Override
    public int getOrder() {
        return -1000;
    }
}
