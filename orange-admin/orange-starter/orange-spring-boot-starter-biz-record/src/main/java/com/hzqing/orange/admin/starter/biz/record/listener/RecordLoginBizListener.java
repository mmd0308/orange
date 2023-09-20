package com.hzqing.orange.admin.starter.biz.record.listener;

import com.hzqing.orange.admin.module.system.record.api.RecordLoginApi;
import com.hzqing.orange.admin.starter.biz.record.converter.RecordConverter;
import com.hzqing.orange.admin.starter.biz.record.dto.RecordDTO;
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
public class RecordLoginBizListener {

    private final RecordLoginApi recordLoginApi;

    public RecordLoginBizListener(RecordLoginApi recordLoginApi) {
        this.recordLoginApi = recordLoginApi;
    }

    @Async("logExecutor")
    @Order
    @EventListener(RecordLoginEvent.class)
    public void saveLog(RecordLoginEvent event) {
        try {
            if (event.getSource() instanceof RecordDTO record) {
                recordLoginApi.add(RecordConverter.INSTANCE.toRecordLogin(record));
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{}", event.getSource());
        }
    }
}
