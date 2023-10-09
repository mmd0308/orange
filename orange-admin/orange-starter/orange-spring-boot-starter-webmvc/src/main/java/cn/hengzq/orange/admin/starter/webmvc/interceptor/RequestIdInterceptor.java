package cn.hengzq.orange.admin.starter.webmvc.interceptor;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hengzq.orange.admin.starter.common.constant.RequestConstant;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

/**
 * @author 程序员橙子
 */
@Slf4j
public class RequestIdInterceptor implements AsyncHandlerInterceptor {

    public RequestIdInterceptor() {
        log.info("init {} complete.", this.getClass().getSimpleName());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String traceId = request.getHeader(RequestConstant.InnerHeader.ORANGE_INNER_REQUEST_ID);
        GlobalContextHelper.removeContext();
        GlobalContextHelper.setContext(StrUtil.isBlank(traceId) ? IdUtil.getSnowflakeNextIdStr() : traceId);
        return true;
    }
}
