package com.hzqing.orange.admin.module.system.permission.common.vo.request;

import com.hzqing.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "部门更新请求")
public class DepartmentUpdateRequest extends BaseTenantVO {

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "排序")
    private Integer sort;
}
