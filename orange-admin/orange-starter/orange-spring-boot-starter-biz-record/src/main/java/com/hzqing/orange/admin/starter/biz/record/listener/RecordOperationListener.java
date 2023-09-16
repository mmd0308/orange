package com.hzqing.orange.admin.starter.biz.record.listener;

import com.hzqing.orange.admin.module.system.record.api.RecordOperationApi;
import com.hzqing.orange.admin.starter.biz.record.converter.RecordConverter;
import com.hzqing.orange.admin.starter.biz.record.dto.RecordDTO;
import com.hzqing.orange.admin.starter.biz.record.event.RecordOperationEvent;
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
public class RecordOperationListener {

    private final RecordOperationApi recordOperationApi;

    public RecordOperationListener(RecordOperationApi recordOperationApi) {
        this.recordOperationApi = recordOperationApi;
    }


    @Async("logExecutor")
    @Order
    @EventListener(RecordOperationEvent.class)
    public void saveLog(RecordOperationEvent event) {
        try {
            if (event.getSource() instanceof RecordDTO record) {
                recordOperationApi.add(RecordConverter.INSTANCE.toRecordOperation(record));
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{}", event.getSource());
        }
    }
}
