package com.hzqing.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "路由元数据")
public class RouterMeta implements Serializable {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "icon")
    private String icon;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private Boolean hidden;

}
