package cn.hengzq.starter.storage.core.local;

import cn.hengzq.starter.storage.core.StorageConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * @author 程序员橙子
 */
@Slf4j
@Data
public class LocalStorageConfig implements StorageConfig {

    /**
     * 本地存储根路径
     */
    private String basePath;

    /**
     * 域名
     */
    private String domain;


    @Override
    public String getDomain() {
        return this.domain;
    }
}
