package cn.hengzq.orange.admin.module.system.record.biz.dto;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonOperationStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RecordOperationListQuery {

    private String traceId;

    private String resourceId;

    private CommonOperationStatusEnum status;

    private LocalDateTime operationStartTime;

    private LocalDateTime operationEndTime;
}
