package com.hzqing.orange.admin.module.system.permission.common.vo;

import com.hzqing.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(description = "用户和角色关系")
public class UserRoleRl extends BaseTenantVO {

    public UserRoleRl(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "角色ID")
    private Long roleId;
}
