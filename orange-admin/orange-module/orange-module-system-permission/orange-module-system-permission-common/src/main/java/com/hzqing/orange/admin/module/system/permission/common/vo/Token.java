package com.hzqing.orange.admin.module.system.permission.common.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "Token响应信息")
@NoArgsConstructor
@AllArgsConstructor
public class Token {

    @Schema(description = "Token")
    private String token;

}
