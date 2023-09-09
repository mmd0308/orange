package com.hzqing.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "资源权限编码")
public class ResourcePermissions {


    @Schema(description = "角色权限标识")
    private List<String> rolePermissions;

    @Schema(description = "菜单权限标识")
    private List<String> menuPermissions;

    @Schema(description = "按钮权限标识")
    private List<String> buttonPermissions;

    @Schema(description = "所有权限标识")
    private List<String> allPermissions;
}
