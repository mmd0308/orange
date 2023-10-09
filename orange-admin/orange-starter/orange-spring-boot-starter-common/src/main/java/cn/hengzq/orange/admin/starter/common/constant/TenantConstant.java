package cn.hengzq.orange.admin.starter.common.constant;

/**
 * 租户常量
 *
 * @author 程序员橙子
 */
public interface TenantConstant {

    /**
     * 默认租户ID列名
     */
    String DEFAULT_TENANT_ID_COLUMN = "tenant_id";

    /**
     * 工作流租户列名
     */
    String ACT_TENANT_ID_COLUMN = "tenant_id_";

    /**
     * 默认系统租户id
     */
    public static final Long DEFAULT_TENANT_ID = -100L;
}
