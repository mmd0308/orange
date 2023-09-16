package com.hzqing.orange.admin.starter.biz.record.config;

import com.hzqing.orange.admin.module.system.record.api.RecordOperationApi;
import com.hzqing.orange.admin.starter.biz.record.aspect.RecordAspect;
import com.hzqing.orange.admin.starter.biz.record.constants.RecordConstants;
import com.hzqing.orange.admin.starter.biz.record.listener.RecordOperationListener;
import com.hzqing.orange.admin.starter.biz.record.properties.RecordProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * @author 程序员橙子
 */
@Slf4j
@EnableAsync
@AutoConfiguration
@EnableConfigurationProperties(value = RecordProperties.class)
@ConditionalOnProperty(prefix = RecordProperties.PREFIX, name = "enabled", havingValue = "true")
public class RecordAutoConfiguration {


    private final RecordProperties recordProperties;

    public RecordAutoConfiguration(RecordProperties recordProperties) {
        this.recordProperties = recordProperties;
        if (log.isDebugEnabled()) {
            log.debug("init {} complete.", this.getClass().getSimpleName());
        }
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(value = RecordOperationApi.class)
    public RecordOperationListener operationRecordListener(RecordOperationApi recordOperationApi) {
        if (log.isDebugEnabled()) {
            log.debug("init {} complete.", this.getClass().getSimpleName());
        }
        return new RecordOperationListener(recordOperationApi);
    }

//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnClass(value = LoginRecordService.class)
//    public LoginRecordBizListener loginRecordListener(LoginRecordService loginRecordService) {
//        if (log.isDebugEnabled()) {
//            log.debug("init {} complete.", this.getClass().getSimpleName());
//        }
//        return new LoginRecordBizListener(loginRecordService);
//    }

    @Bean
    public RecordAspect logAspect(ApplicationContext applicationContext) {
        if (log.isDebugEnabled()) {
            log.info("init {} complete.", this.getClass().getSimpleName());
        }
        return new RecordAspect(applicationContext, recordProperties);
    }

    @Bean
    public Executor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(1);
        //配置最大线程数
        executor.setMaxPoolSize(1);
        //配置队列大小
        executor.setQueueCapacity(10);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-operation-log-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();
        if (log.isDebugEnabled()) {
            log.debug("{} init logExecutor.", RecordConstants.SERVICE_NAME);
        }
        return executor;
    }
}
