package cn.hengzq.orange.admin.starter.biz.record.event;

import cn.hengzq.orange.admin.starter.biz.record.dto.RecordDTO;
import org.springframework.context.ApplicationEvent;

/**
 * 登录日志事件
 *
 * @author 程序员橙子
 */
public class RecordLoginEvent extends ApplicationEvent {

    public RecordLoginEvent(RecordDTO source) {
        super(source);
    }
}
