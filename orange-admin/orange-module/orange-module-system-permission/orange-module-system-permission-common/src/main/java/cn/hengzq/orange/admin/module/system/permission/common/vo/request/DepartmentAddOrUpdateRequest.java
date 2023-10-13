package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import cn.hengzq.orange.admin.module.system.permission.common.exception.support.DepartmentErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "部门管理-新增或更新参数")
public class DepartmentAddOrUpdateRequest implements Serializable {

    @Schema(description = "父级ID,默认:-1（为顶级部门）")
    private Long parentId;

    @NotBlank(message = DepartmentErrorCode.DEPARTMENT_NAME_CANNOT_NULL_KEY)
    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    @Size(max = 500, message = "长度必须小于等于500")
    private String remark;
}
