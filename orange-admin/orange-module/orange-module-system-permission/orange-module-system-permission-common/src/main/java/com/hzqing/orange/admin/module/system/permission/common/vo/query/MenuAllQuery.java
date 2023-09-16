package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * @author 程序员橙子
 */
@Schema(description = "菜单管理-查询全部参数")
@Data
public class MenuAllQuery implements Serializable {

    @Schema(description = "菜单名称")
    private String name;
}
