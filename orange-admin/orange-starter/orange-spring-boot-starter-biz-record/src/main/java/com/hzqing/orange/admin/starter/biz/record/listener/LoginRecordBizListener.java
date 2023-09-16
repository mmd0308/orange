package com.hzqing.orange.admin.starter.biz.record.listener;

import com.hzqing.orange.admin.starter.biz.record.event.RecordLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 日志监听
 *
 * @author 程序员橙子
 */
@Slf4j
public class LoginRecordBizListener {
    @Async("logExecutor")
    @Order
    @EventListener(RecordLoginEvent.class)
    public void saveLog(RecordLoginEvent event) {
        try {
//            LoginRecord record = (LoginRecord) event.getSource();
//            loginRecordService.add(record);
        } catch (Exception e) {
            log.error("save Log is error. msg:{}", event.getSource());
        }
    }
}
