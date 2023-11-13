package cn.hengzq.orange.admin.starter.biz.record.listener;

import cn.hengzq.orange.admin.module.system.record.api.RecordLoginApi;
import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordLoginVO;
import cn.hengzq.orange.admin.starter.biz.record.convert.RecordConverter;
import cn.hengzq.orange.admin.starter.biz.record.event.RecordLoginEvent;
import cn.hengzq.orange.admin.starter.common.util.IPAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDateTime;
import java.util.Objects;

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
                record.setUserLocation(IPAddressUtil.getCacheAddressByIP(record.getUserIp()));
                RecordLoginVO recordLoginVO = RecordConverter.INSTANCE.toRecordLogin(record);
                if (Objects.isNull(recordLoginVO.getLoginTime())) {
                    recordLoginVO.setLoginTime(LocalDateTime.now());
                }
                recordLoginApi.add(recordLoginVO);
            } else {
                log.warn("params type error.");
            }
        } catch (Exception e) {
            log.error("save Log is error. msg:{},data:{}", e.getMessage(), event.getSource());
        }
    }
}
