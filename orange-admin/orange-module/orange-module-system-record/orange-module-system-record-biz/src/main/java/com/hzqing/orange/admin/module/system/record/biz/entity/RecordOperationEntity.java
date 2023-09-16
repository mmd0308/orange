package com.hzqing.orange.admin.module.system.record.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.RequestMethodEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import com.hzqing.orange.admin.starter.mybatis.handlers.EnumCodeTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@TableName("sys_record_operation")
@Data
public class RecordOperationEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("trace_id")
    private String traceId;

    /**
     * 操作接口ID
     */
    @TableField("resource_id")
    private String resourceId;

    /**
     * 请求URL
     */
    @TableField("request_url")
    private String requestUrl;

    @TableField(value = "request_method", typeHandler = EnumCodeTypeHandler.class)
    private RequestMethodEnum requestMethod;

    @TableField("java_method")
    private String javaMethod;

    @TableField("java_method_args")
    private String javaMethodArgs;

    @TableField("java_method_result")
    private String javaMethodResult;

    @TableField("stack_trace")
    private String stackTrace;

    @TableField("user_id")
    private Long userId;

    @TableField("user_ip")
    private String userIp;

    @TableField("user_location")
    private String userLocation;

    @TableField("user_agent")
    private String userAgent;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private CommonOperationStatusEnum status;

    /**
     * 请求耗时
     */
    @TableField("execute_time")
    private Long executeTime;


}
