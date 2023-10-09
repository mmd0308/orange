package cn.hengzq.orange.admin.starter.biz.record.event;

import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author 程序员橙子
 */
public class RecordOperationEvent extends ApplicationEvent {

    public RecordOperationEvent(RecordDTO source) {
        super(source);
    }
}
