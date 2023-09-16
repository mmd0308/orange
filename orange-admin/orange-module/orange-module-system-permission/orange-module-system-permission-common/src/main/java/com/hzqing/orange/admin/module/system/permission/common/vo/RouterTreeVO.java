package com.hzqing.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "路由-树型结构")
public class RouterTreeVO implements Serializable {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "parentId")
    private Long parentId;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "请求路径")
    private String path;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private Boolean hidden;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "路由子集")
    private List<RouterTreeVO> children;
}