package cn.hengzq.orange.admin.module.system.permission.biz.controller;

import cn.hengzq.orange.admin.module.system.permission.biz.service.AuthService;
import cn.hengzq.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import cn.hengzq.orange.admin.module.system.permission.common.vo.Token;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserInfo;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.LoginRequest;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-认证管理管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "账号密码登陆", operationId = "system:permission:auth:login")
    @PostMapping(value = "/login")
    public Result<Token> login(@Validated @RequestBody LoginRequest request) {
        Token tokenVo = authService.login(request);
        return ResultWrapper.ok(tokenVo);
    }

    @Operation(summary = "账号退出登录", operationId = "system:permission:auth:logout")
    @GetMapping(value = "/logout")
    public Result<Void> logout() {
        return ResultWrapper.ok();
    }

    @Operation(summary = "用户当前登陆的用户信息", operationId = "system:permission:auth:info")
    @GetMapping("/info")
    public Result<UserInfo> getInfo() {
        UserInfo userInfo = authService.getInfo();
        return ResultWrapper.ok(userInfo);
    }

    @Operation(summary = "密码加密 只用于API测试使用", operationId = "system:permission:auth:password-encrypt")
    @GetMapping("/password-encrypt")
    public Result<String> passwordEncrypt(@RequestParam String password) {
        return ResultWrapper.ok(authService.passwordEncrypt(password));
    }

}
