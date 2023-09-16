package com.hzqing.orange.admin.module.system.record.common.vo.query;

import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "操作记录-查询所有参数")
public class OperationRecordAllQuery implements Serializable {

    @Schema(description = "请求ID")
    private String traceId;

    @Schema(description = "操作结果")
    private CommonOperationStatusEnum status;

    @Schema(description = "操作开始时间")
    private LocalDateTime operationStartTime;

    @Schema(description = "操作结束时间")
    private LocalDateTime operationEndTime;

}
