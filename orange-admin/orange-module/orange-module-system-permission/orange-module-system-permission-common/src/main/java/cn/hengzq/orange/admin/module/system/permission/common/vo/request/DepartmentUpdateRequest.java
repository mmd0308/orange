package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "部门管理-更新参数")
public class DepartmentUpdateRequest implements Serializable {

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;
}
