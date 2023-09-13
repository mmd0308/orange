package com.hzqing.orange.admin.module.system.dict.common.config;

import com.hzqing.orange.admin.starter.common.message.GlobalMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * @author 程序员橙子
 */
@Slf4j
@AutoConfiguration
public class MessageSourceAutoConfiguration {

    public MessageSourceAutoConfiguration() {
        if (log.isDebugEnabled()) {
            log.info("init {} complete.", this.getClass().getSimpleName());
        }
        // 加载错误码信息
        GlobalMessageSource.getInstance().addBasenames("com.hzqing.orange.admin.module.system.dict.common.messages");
    }

}
