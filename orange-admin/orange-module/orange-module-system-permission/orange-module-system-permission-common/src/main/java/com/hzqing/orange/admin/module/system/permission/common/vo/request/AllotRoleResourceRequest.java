package com.hzqing.orange.admin.module.system.permission.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
public class AllotRoleResourceRequest implements Serializable {


    @NotNull(message = "角色ID不能为空.")
    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long roleId;

    @NotEmpty(message = "菜单ID不能为空")
    @Schema(description = "菜单ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<Long> menuIds;

    @Schema(description = "按钮ID")
    private List<Long> buttonIds;
}
