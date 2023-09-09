package com.hzqing.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "路由树型结构")
public class RouterTree implements Serializable {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "parentId")
    private Long parentId;

    @Schema(description = "组件名称")
    private String component;

    @Schema(description = "请求路径")
    private String path;

    @Schema(description = "路由元数据")
    private RouterMeta meta;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "路由子集")
    private List<RouterTree> children;
}