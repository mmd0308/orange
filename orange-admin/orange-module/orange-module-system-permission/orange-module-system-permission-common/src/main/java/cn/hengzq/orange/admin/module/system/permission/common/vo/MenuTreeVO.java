package cn.hengzq.orange.admin.module.system.permission.common.vo;

import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "菜单树型结构")
public class MenuTreeVO extends BaseTenantVO {

    @Schema(description = "菜单ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "预设标记", accessMode = Schema.AccessMode.READ_ONLY)
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "子集")
    private List<MenuTreeVO> children;
}
