package com.hzqing.orange.admin.starter.context;

import cn.hutool.core.util.StrUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.hzqing.orange.admin.starter.common.constants.TenantConstants;
import com.hzqing.orange.admin.starter.context.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 程序员橙子
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalContext {

    private static final TransmittableThreadLocal<GlobalContext> LOCAL = new TransmittableThreadLocal<>() {
        @Override
        protected GlobalContext initialValue() {
            return new GlobalContext();
        }
    };

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 租户ID的列名
     */
    private String tenantIdColumn = TenantConstants.DEFAULT_TENANT_ID_COLUMN;

    /**
     * 日志追踪ID
     */
    private String traceId;

    /**
     * 当前上下文中的用户
     */
    private User user;

    public static GlobalContext getContext() {
        return LOCAL.get();
    }

    public static void removeContext() {
        LOCAL.remove();
    }

    public static void setContext(GlobalContext context) {
        LOCAL.set(context);
    }

    public GlobalContext setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public GlobalContext setTenantIdColumn(String tenantIdColumn) {
        this.tenantIdColumn = tenantIdColumn;
        return this;
    }

    public GlobalContext setTraceId(String traceId) {
        this.traceId = traceId;
        if (StrUtil.isNotBlank(traceId)) {
            Thread.currentThread().setName(traceId);
        }
        return this;
    }

    public GlobalContext setUser(User user) {
        this.user = user;
        return this;
    }
}