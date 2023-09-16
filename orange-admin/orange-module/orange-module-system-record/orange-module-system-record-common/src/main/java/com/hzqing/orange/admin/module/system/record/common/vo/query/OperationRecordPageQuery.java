package com.hzqing.orange.admin.module.system.record.common.vo.query;

import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import com.hzqing.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "操作记录-分页查询")
public class OperationRecordPageQuery extends PageQuery {

    @Schema(description = "请求ID")
    private String traceId;

    @Schema(description = "操作结果")
    private CommonOperationStatusEnum status;

    @Schema(description = "操作开始时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationStartTime;

    @Schema(description = "操作结束时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationEndTime;

}
