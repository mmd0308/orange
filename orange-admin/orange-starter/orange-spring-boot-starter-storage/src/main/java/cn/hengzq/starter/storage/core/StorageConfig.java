package cn.hengzq.starter.storage.core;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author 程序员橙子
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public interface StorageConfig {

    /**
     * 获取配置域名
     */
    String getDomain();
}