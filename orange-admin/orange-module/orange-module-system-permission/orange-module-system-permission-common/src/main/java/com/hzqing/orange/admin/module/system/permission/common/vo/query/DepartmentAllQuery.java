package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "部门管理-查询所有的数据")
public class DepartmentAllQuery implements Serializable {

    @Schema(description = "部门名称")
    private String name;

    @Schema(description = "部门名称,模糊匹配")
    private String nameLike;

    @Schema(description = "父级ID")
    private Long parentId;

}
