package com.hzqing.orange.admin.module.system.record.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.module.system.record.common.constants.RecordLoginTypeEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonOperationStatusEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import com.hzqing.orange.admin.starter.mybatis.handlers.EnumCodeTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_record_login")
public class RecordLoginEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("trace_id")
    private String traceId;

    private String account;

    /**
     * 操作类型,0-登陆 1-登出
     */
    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private RecordLoginTypeEnum type;

    @TableField("user_id")
    private Long userId;

    @TableField("user_ip")
    private String userIp;

    @TableField("user_location")
    private String userLocation;

    @TableField("user_agent")
    private String userAgent;

    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
     * 请求状态（0成功 1失败）
     */
    @TableField(typeHandler = EnumCodeTypeHandler.class)
    private CommonOperationStatusEnum status;
}
