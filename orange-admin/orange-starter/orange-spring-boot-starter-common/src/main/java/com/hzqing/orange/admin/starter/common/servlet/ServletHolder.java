package com.hzqing.orange.admin.starter.common.servlet;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * request请求类
 *
 * @author 程序员橙子
 */
@Slf4j
public class ServletHolder {

    public static ServletRequestAttributes getServletRequestAttributes() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            return (ServletRequestAttributes) requestAttributes;
        } catch (Exception e) {
            log.warn("getServletRequestAttributes is null.");
            return null;
        }
    }

    public static HttpServletRequest getHttpServletRequest() {
        try {
            return getServletRequestAttributes().getRequest();
        } catch (Exception e) {
            log.warn("getHttpServletRequest is null.");
            return null;
        }
    }

    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return StrUtil.isBlank(ua) ? StrUtil.EMPTY : ua;
    }

    /**
     * 返回附件
     *
     * @param response 响应
     * @param filename 文件名
     * @param content  附件内容
     */
    public static void writeAttachment(HttpServletResponse response, String filename, byte[] content) throws IOException {
        // 设置 header 和 contentType
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        // 输出附件
        IoUtil.write(response.getOutputStream(), false, content);
    }

}
