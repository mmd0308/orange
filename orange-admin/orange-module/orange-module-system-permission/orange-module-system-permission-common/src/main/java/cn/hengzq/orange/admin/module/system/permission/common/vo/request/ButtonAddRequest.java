package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "按钮新增请求参数")
@Data
public class ButtonAddRequest implements Serializable {

    @NotNull(message = "菜单ID不能为空.")
    @Schema(description = "菜单ID")
    private Long menuId;

    @NotNull(message = "菜单名称不能为空.")
    @Schema(description = "菜单名称")
    private String name;

    @NotNull(message = "权限编码不能为空.")
    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
