package cn.hengzq.orange.admin.starter.biz.record.listener;

import cn.hengzq.orange.admin.module.system.record.api.RecordOperationApi;
import cn.hengzq.orange.admin.starter.biz.record.convert.RecordConverter;
import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import cn.hengzq.orange.admin.starter.biz.record.event.RecordOperationEvent;
import cn.hengzq.orange.admin.starter.common.util.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

/**
 * 操作记录监听
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
                record.setUserLocation(IPAddressUtil.getCacheAddressByIP(record.getUserIp()));
                recordOperationApi.add(RecordConverter.INSTANCE.toRecordOperation(record));
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{}", event.getSource());
        }
    }
}
