package cn.hengzq.orange.admin.module.system.permission.common.vo;

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
@Schema(description = "部门")
public class DepartmentTreeVO extends BaseTenantVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "子集")
    private List<DepartmentTreeVO> children;
}
