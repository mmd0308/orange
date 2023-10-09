package cn.hengzq.orange.admin.module.system.permission.common.vo;

import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "按钮")
@Data
public class ButtonVO extends BaseTenantVO {

    @Schema(description = "按钮ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotNull(message = "菜单ID不能为空.")
    @Schema(description = "菜单ID")
    private Long menuId;

    @Schema(description = "rootId", accessMode = Schema.AccessMode.READ_ONLY)
    private Long rootId;

    @NotNull(message = "菜单名称不能为空.")
    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "预设标记", accessMode = Schema.AccessMode.READ_ONLY)
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
