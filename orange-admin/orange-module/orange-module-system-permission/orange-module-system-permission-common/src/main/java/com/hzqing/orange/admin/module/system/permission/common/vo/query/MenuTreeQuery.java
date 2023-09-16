package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "菜单管理-树型结构查询参数")
public class MenuTreeQuery implements Serializable {

    @Schema(description = "父级别ID")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "权限编码,精确查询")
    private String permission;

    @Schema(description = "权限编码,模糊匹配")
    private String permissionLike;

}
