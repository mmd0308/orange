package cn.hengzq.orange.admin.starter.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTenantVO extends BaseVO {

    @Schema(description = "租户id", accessMode = Schema.AccessMode.READ_ONLY)
    private Long tenantId;

}
