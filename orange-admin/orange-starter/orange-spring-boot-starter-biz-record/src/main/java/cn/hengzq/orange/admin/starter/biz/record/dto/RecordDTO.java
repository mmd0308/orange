package cn.hengzq.orange.admin.starter.biz.record.dto;

import cn.hengzq.orange.admin.module.system.record.common.constant.RecordLoginTypeEnum;
import cn.hengzq.orange.admin.starter.common.enums.support.CommonOperationStatusEnum;
import cn.hengzq.orange.admin.starter.common.enums.support.RequestMethodEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class RecordDTO implements Serializable {

    /**
     * 请求ID
     */
    private String traceId;

    /**
     * 操作资源ID
     */
    private String resourceId;

    /**
     * 登录类型
     */
    private RecordLoginTypeEnum type;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求方式 GET,POST,PUT,PATCH,DELETE
     */
    private RequestMethodEnum requestMethod;

    /**
     * Java 方法名
     */
    private String javaMethod;

    /**
     * Java 方法名参数
     */
    private String javaMethodArgs;

    /**
     * Java 方法返回结果
     */
    private String javaMethodResult;

    /**
     * 异常堆栈信息
     */
    private String stackTrace;

    /**
     * 操作用户
     */
    private Long userId;

    /**
     * 登录账号
     */
    private String account;

    /**
     * 操作用户IP
     */
    private String userIp;

    /**
     * 用户操作地点
     */
    private String userLocation;

    /**
     * User-Agent
     */
    private String userAgent;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 请求状态
     */
    private CommonOperationStatusEnum status;

    /**
     * 请求耗时,单位毫秒
     */
    private Long executeTime;
}
