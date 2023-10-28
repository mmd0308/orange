package cn.hengzq.starter.storage.config;

import cn.hengzq.starter.storage.core.StorageServiceFactory;
import cn.hengzq.starter.storage.core.impl.StorageServiceFactoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class StorageAutoConfiguration {
    public StorageAutoConfiguration() {
        if (log.isDebugEnabled()) {
            log.debug("init {} complete.", this.getClass().getSimpleName());
        }
    }
    @Bean
    public StorageServiceFactory storageServiceFactory() {
        return new StorageServiceFactoryImpl();
    }

}
