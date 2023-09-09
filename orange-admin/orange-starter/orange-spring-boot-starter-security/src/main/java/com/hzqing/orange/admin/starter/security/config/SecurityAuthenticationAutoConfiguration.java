package com.hzqing.orange.admin.starter.security.config;

import com.hzqing.orange.admin.starter.security.service.PermissionAuthenticationService;
import com.hzqing.orange.admin.starter.security.service.SecurityAuthenticationService;
import com.hzqing.orange.admin.starter.security.service.impl.SecurityAuthenticationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 安全认证自动装配
 *
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class SecurityAuthenticationAutoConfiguration {

    public SecurityAuthenticationAutoConfiguration() {
        if (log.isDebugEnabled()) {
            log.debug("init {}.", this.getClass().getSimpleName());
        }
    }

    @Bean("ss")
    public SecurityAuthenticationService securityAuthenticationService(PermissionAuthenticationService permissionAuthenticationService) {
        if (log.isDebugEnabled()) {
            log.debug("init SecurityAuthenticationServiceImpl complete.");
        }
        return new SecurityAuthenticationServiceImpl(permissionAuthenticationService);
    }

}
