package com.hzqing.orange.admin.starter.context.config;

import com.hzqing.orange.admin.starter.context.OrangeApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
public class ContextAutoConfiguration {

    @Bean
    OrangeApplicationContext orangeApplicationContext() {
        return new OrangeApplicationContext();
    }
}