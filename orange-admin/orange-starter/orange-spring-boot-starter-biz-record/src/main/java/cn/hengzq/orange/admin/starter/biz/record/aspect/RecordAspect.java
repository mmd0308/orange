package cn.hengzq.orange.admin.starter.biz.record.aspect;

import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import cn.hengzq.orange.admin.starter.biz.record.event.RecordOperationEvent;
import cn.hengzq.orange.admin.starter.biz.record.properties.RecordProperties;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.json.JSONUtil;
import cn.hengzq.orange.admin.module.system.record.common.constants.RecordLoginTypeEnum;
import cn.hengzq.orange.admin.starter.biz.record.event.RecordLoginEvent;
import cn.hengzq.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import cn.hengzq.orange.admin.starter.common.constants.enums.support.RequestMethodEnum;
import cn.hengzq.orange.admin.starter.common.servlet.ServletHolder;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 日志拦截器
 *
 * @author 程序员橙子
 */
@Slf4j
@Aspect
public class RecordAspect {

    private final ApplicationContext applicationContext;

    private final RecordProperties properties;

    private final AntPathMatcher pathMatcher;

    private static final Set<String> OPERATION_RECORD_SKIP_URL_LIST = Set.of("/**/record-operation/**", "/**/v3/api-docs",
            "/**/v3/api-docs/**");
    private static final List<String> LOGIN_URL_LIST = List.of("/**/login");
    private static final List<String> LOGOUT_URL_LIST = List.of("/**/logout");

    public RecordAspect(ApplicationContext applicationContext, RecordProperties properties) {
        this.applicationContext = applicationContext;
        this.pathMatcher = new AntPathMatcher();
        this.properties = properties;
        if (CollUtil.isEmpty(this.properties.getIgnoreUrls())) {
            this.properties.setIgnoreUrls(OPERATION_RECORD_SKIP_URL_LIST);
        } else {
            this.properties.getIgnoreUrls().addAll(OPERATION_RECORD_SKIP_URL_LIST);
        }
    }

    @Around("@annotation(operation)")
    public Object record(ProceedingJoinPoint point, Operation operation) throws Throwable {
        // 请求开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            Object result = point.proceed();
            this.process(point, operation, startTime, result, null);
            return result;
        } catch (Throwable exception) {
            this.process(point, operation, startTime, null, exception);
            throw exception;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("operation log is complete.");
            }
        }
    }

    private void process(ProceedingJoinPoint point, Operation operation, LocalDateTime startTime, Object result, Throwable exception) {
        HttpServletRequest request = ServletHolder.getHttpServletRequest();
        if (ObjectUtils.isEmpty(request)) {
            log.warn("request is null.");
            return;
        }
        String requestURI = request.getRequestURI();
        if (ignoreUrl(request)) {
            return;
        }
        RecordDTO record = generateRecord(point, request, operation, startTime, result, exception);
        if (isMatch(requestURI, LOGIN_URL_LIST)) {
            record.setType(RecordLoginTypeEnum.LOGIN);
            applicationContext.publishEvent(new RecordLoginEvent(record));
        } else if (isMatch(requestURI, LOGOUT_URL_LIST)) {
            record.setType(RecordLoginTypeEnum.LOGOUT);
            applicationContext.publishEvent(new RecordLoginEvent(record));
        } else {
            applicationContext.publishEvent(new RecordOperationEvent(record));
        }
    }

    private RecordDTO generateRecord(ProceedingJoinPoint point, HttpServletRequest request, Operation operation,
                                     LocalDateTime startTime, Object result, Throwable exception) {
        LocalDateTime endTime = LocalDateTime.now();
        RecordDTO record = RecordDTO.builder()
                .resourceId(getResourceId(point, operation))
                .traceId(GlobalContextHelper.getTraceId())
                .requestUrl(request.getRequestURI())
                .requestMethod(RequestMethodEnum.valueOf(request.getMethod()))
                .javaMethod(point.getSignature().toString())
                .javaMethodArgs(JSONUtil.toJsonStr(point.getArgs()))
                .javaMethodResult(JSONUtil.toJsonStr(result))
                .userId(GlobalContextHelper.getCurrentUserId())
                .userAgent(ServletHolder.getUserAgent(request))
                .startTime(startTime)
                .endTime(endTime)
                .executeTime(LocalDateTimeUtil.between(startTime, endTime).toMillis())
                .status(Objects.isNull(exception) ? CommonOperationStatusEnum.SUCCESS : CommonOperationStatusEnum.FAIL)
                .build();

        if (Objects.nonNull(exception)) {
            record.setStackTrace(ExceptionUtil.stacktraceToString(exception));
        }
        String clientIP = JakartaServletUtil.getClientIP(request);
        clientIP = "0:0:0:0:0:0:0:1".equals(clientIP) ? "127.0.0.1" : clientIP;
        record.setUserIp(clientIP);

        if (Objects.nonNull(GlobalContextHelper.getGlobalContext().getUser())) {
            record.setAccount(GlobalContextHelper.getGlobalContext().getUser().getUsername());
        }
        return record;
    }

    /**
     * 获取请求资源ID
     */
    private String getResourceId(ProceedingJoinPoint point, Operation operation) {
        String resourceId = operation.operationId();
        if (StrUtil.isBlank(resourceId)) {
            MethodSignature methodSignature = (MethodSignature) point.getSignature();
            Method method = methodSignature.getMethod();
            PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
            if (Objects.nonNull(preAuthorize) && StrUtil.isNotBlank(preAuthorize.value())) {
                resourceId = StrUtil.subBetween(preAuthorize.value(), "('", "')");
            }
        }
        return resourceId;
    }

    /**
     * URL 是否匹配
     *
     * @param url  请求URL
     * @param list 匹配集合
     */
    private boolean isMatch(String url, List<String> list) {
        if (CollUtil.isEmpty(list) || StrUtil.isBlank(url)) {
            return false;
        }
        for (String pattern : list) {
            if (pathMatcher.match(pattern, url)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 忽略的URL
     */
    private boolean ignoreUrl(HttpServletRequest request) {
        if (CollUtil.isEmpty(this.properties.getIgnoreUrls())) {
            return false;
        }
        for (String pattern : this.properties.getIgnoreUrls()) {
            if (pathMatcher.match(pattern, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }
}
