package com.hzqing.orange.admin.starter.webmvc.config;

import com.hzqing.orange.admin.starter.webmvc.constants.FilterOrderConstants;
import com.hzqing.orange.admin.starter.webmvc.constants.WebmvcConstants;
import com.hzqing.orange.admin.starter.webmvc.filter.RequestIdFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Slf4j
@AutoConfiguration
public class FilterAutoConfiguration {

    /**
     * 注册 RequestId 过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<RequestIdFilter> registrationRequestIdFilter() {
        FilterRegistrationBean<RequestIdFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new RequestIdFilter());
        filterRegistrationBean.setName("requestIdFilter");
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(FilterOrderConstants.REQUEST_ID_FILTER);
        if (log.isDebugEnabled()) {
            log.debug("{} init RequestIdFilter complete.", WebmvcConstants.SERVICE_NAME);
        }
        return filterRegistrationBean;
    }

}