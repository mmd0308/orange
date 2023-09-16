package com.hzqing.orange.admin.starter.biz.record.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author 程序员橙子
 * @ 日志配置
 */
@Data
@Component
@ConfigurationProperties(RecordProperties.PREFIX)
public class RecordProperties {

    /**
     * 前缀
     */
    public static final String PREFIX = "orange.system.record";

    /**
     * 是否启用
     */
    private boolean enabled;

    /**
     * 操作记录需要忽略的请求
     */
    private Set<String> ignoreUrls = Set.of();

}
