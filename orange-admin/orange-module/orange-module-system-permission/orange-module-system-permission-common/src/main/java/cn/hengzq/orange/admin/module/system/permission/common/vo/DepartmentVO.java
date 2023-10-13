package cn.hengzq.orange.admin.module.system.permission.common.vo;

import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "部门")
public class DepartmentVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
