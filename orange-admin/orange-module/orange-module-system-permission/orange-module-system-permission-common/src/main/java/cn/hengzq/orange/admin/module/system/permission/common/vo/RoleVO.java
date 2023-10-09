package cn.hengzq.orange.admin.module.system.permission.common.vo;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonDataStatusEnum;
import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "角色")
@Data
public class RoleVO extends BaseTenantVO {

    @Schema(description = "角色id", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "角色权限字符串")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private CommonDataStatusEnum status;

    @Schema(description = "预设标记")
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "备注")
    private String remark;

}
