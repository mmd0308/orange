package com.hzqing.orange.admin.module.system.record.common.vo;

import com.hzqing.orange.admin.module.system.record.common.constants.RecordLoginTypeEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import com.hzqing.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 登陆日志实体类
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "登录记录")
public class RecordLoginVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "请求ID")
    private String traceId;

    @Schema(description = "登录账号")
    private String account;

    @Schema(description = "操作类型")
    private RecordLoginTypeEnum type;

    @Schema(description = "操作用户ID")
    private Long userId;

    private String userIp;

    @Schema(description = "User-Agent")
    private String userAgent;

    @Schema(description = "登录时间")
    private LocalDateTime loginTime;

    private String userLocation;

    @Schema(description = "操作状态")
    private CommonOperationStatusEnum status;
}
