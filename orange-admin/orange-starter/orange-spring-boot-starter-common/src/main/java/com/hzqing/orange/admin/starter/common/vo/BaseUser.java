package com.hzqing.orange.admin.starter.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础的用户实体类
 *
 * @author 程序员橙子
 */
@Data
public class BaseUser implements Serializable {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名称")
    private String name;
}
