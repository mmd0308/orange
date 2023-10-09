package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "菜单更新请求参数")
@Data
public class MenuUpdateRequest implements Serializable {

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private Boolean hidden;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
