package com.hzqing.orange.admin.starter.security.config;


import com.hzqing.orange.admin.module.system.permission.api.PermissionApi;
import com.hzqing.orange.admin.module.system.permission.api.UserApi;
import com.hzqing.orange.admin.starter.security.service.PermissionAuthenticationService;
import com.hzqing.orange.admin.starter.security.service.impl.PermissionAuthenticationMonomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 只要依赖Starter 默认自动装配的Bean
 *
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class SecurityAutoConfiguration {

    public SecurityAutoConfiguration() {
        if (log.isDebugEnabled()) {
            log.debug("init {}.", this.getClass().getSimpleName());
        }
    }

    /**
     * Spring Security 加密
     */
    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = UserApi.class)
    public PermissionAuthenticationService permissionAuthenticationService(UserApi userApi, PermissionApi permissionApi) {
        return new PermissionAuthenticationMonomerServiceImpl(userApi, permissionApi);
    }

}