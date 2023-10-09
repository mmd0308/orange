package cn.hengzq.orange.admin.starter.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author 程序员橙子
 */
public class BaseTenantVO extends BaseVO {

    @Schema(description = "租户id", accessMode = Schema.AccessMode.READ_ONLY)
    private Long tenantId;

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
