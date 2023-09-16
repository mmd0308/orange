package com.hzqing.orange.admin.module.system.record.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "操作记录-导出查询")
public class OperationRecordExportQuery implements Serializable {

    @Schema(description = "请求ID")
    private String traceId;

    @Schema(description = "模块名称")
    private String module;

    @Schema(description = "开始时间 第一个为开始时间 第二个为结束时间")
    private List<LocalDateTime> operationTime;

}
