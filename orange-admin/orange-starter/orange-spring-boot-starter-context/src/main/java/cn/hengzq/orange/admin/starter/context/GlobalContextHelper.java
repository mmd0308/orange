package cn.hengzq.orange.admin.starter.context;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hengzq.orange.admin.starter.context.dto.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 程序员橙子
 */
@Slf4j
public class GlobalContextHelper {

    public static void setContext(GlobalContext context) {
        if (context == null) {
            log.warn("set context failed. context is null.");
            return;
        }
        GlobalContext.setContext(context);
    }

    public static GlobalContext getGlobalContext() {
        return GlobalContext.getContext();
    }

    public static void removeContext() {
        GlobalContext.removeContext();
    }


    public static void setContext(Long tenantId) {
        setContext(tenantId, null);
    }

    public static void setContext(String traceId) {
        getGlobalContext().setTraceId(StrUtil.isBlank(traceId) ? IdUtil.getSnowflakeNextIdStr() : traceId);
    }


    public static void setContext(Long tenantId, Long userId) {
        if (tenantId == null) {
            log.warn("set context failed. tenantId is null.");
            return;
        }
        getGlobalContext().setTenantId(tenantId)
                .setUser(User.builder().userId(userId).build());
    }

    public static void setContext(Long tenantId, Long userId, String username) {
        if (tenantId == null) {
            log.warn("set context failed. tenantId is null.");
            return;
        }
        getGlobalContext().setTenantId(tenantId)
                .setUser(User.builder().userId(userId).username(username).build());
    }

    public static Long getTenantId() {
        if (GlobalContext.getContext() == null) {
            log.warn("context is null.");
            return null;
        }
        return GlobalContext.getContext().getTenantId();
    }

    public static String getTenantIdColumn() {
        if (GlobalContext.getContext() == null) {
            log.warn("context is null.");
            return null;
        }
        return GlobalContext.getContext().getTenantIdColumn();
    }

    public static Long getCurrentUserId() {
        if (GlobalContext.getContext() == null) {
            log.warn("system context is null.");
            return null;
        }
        if (GlobalContext.getContext().getUser() == null) {
            log.warn("system context user is null.");
            return null;
        }
        return GlobalContext.getContext().getUser().getUserId();
    }


    public static String getTraceId() {
        if (getGlobalContext() == null) {
            log.warn("system context is null.");
            return null;
        }
        return getGlobalContext().getTraceId();
    }


}
