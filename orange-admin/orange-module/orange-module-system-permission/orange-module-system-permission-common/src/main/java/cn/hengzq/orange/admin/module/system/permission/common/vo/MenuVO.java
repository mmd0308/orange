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
@Schema(description = "菜单")
@Data
public class MenuVO extends BaseTenantVO {

    @Schema(description = "菜单ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "rootId", accessMode = Schema.AccessMode.READ_ONLY)
    private Long rootId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "预设标记 0-预置数据 1-自定义数据")
    private DataPresetFlagEnum presetFlag;

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

    @Schema(description = "菜单关联按钮")
    private List<ButtonVO> buttonVOList;
}
