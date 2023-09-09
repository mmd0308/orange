package com.hzqing.orange.admin.module.system.permission.common.vo.request;


import com.hzqing.orange.admin.starter.common.constants.TenantConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "登陆参数")
public class LoginRequest {

    @Schema(description = "登陆账号")
    @NotBlank(message = "登陆账号不能为空.")
    private String username;

    @Schema(description = "登陆密码")
    @NotBlank(message = "登陆密码不能为空.")
    private String password;

    @Schema(description = "租户ID, 默认:-100", defaultValue = "-100")
    private Long tenantId = TenantConstants.DEFAULT_TENANT_ID;

}
