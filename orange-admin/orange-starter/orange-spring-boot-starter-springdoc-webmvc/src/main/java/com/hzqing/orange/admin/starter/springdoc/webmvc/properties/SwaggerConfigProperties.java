package com.hzqing.orange.admin.starter.springdoc.webmvc.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = SwaggerConfigProperties.PREFIX)
public class SwaggerConfigProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "orange.swagger";

    /**
     * 是否开启
     */
    private boolean enabled;

    /**
     * 项目名称
     */
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
