package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "查询所有的角色参数")
@Data
public class RoleAllQuery implements Serializable {

    @Schema(description = "角色名称")
    private String name;
}
