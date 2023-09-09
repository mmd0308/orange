package com.hzqing.orange.admin.starter.webmvc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class WebmvcAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("initialize addInterceptors.");
        //  添加拦截器 顺序不可调整
//        registry.addInterceptor(new RequestIdInterceptor());

        // 上下文设置
//        registry.addInterceptor(new SecurityContextInterceptor());
    }

}