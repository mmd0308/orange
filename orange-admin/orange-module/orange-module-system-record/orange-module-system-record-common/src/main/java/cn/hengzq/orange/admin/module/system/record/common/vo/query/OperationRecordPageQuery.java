package cn.hengzq.orange.admin.module.system.record.common.vo.query;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonOperationStatusEnum;
import cn.hengzq.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "操作记录-分页查询")
public class OperationRecordPageQuery extends PageQuery {

    @Schema(description = "请求ID")
    private String traceId;

    @Schema(description = "资源ID")
    private String resourceId;

    @Schema(description = "操作结果")
    private CommonOperationStatusEnum status;

    @Schema(description = "操作开始时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationStartTime;

    @Schema(description = "操作结束时间 格式:yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operationEndTime;

}
