package com.hzqing.orange.admin.module.system.permission.biz.controller;

import  com.hzqing.orange.admin.module.system.permission.biz.service.PermissionService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.AllotRoleResource;
import com.hzqing.orange.admin.module.system.permission.common.vo.AllotUserRole;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-权限变更相关接口")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    @PreAuthorize("@ss.hasPermission('system:permission:permission:allot-user-role')")
    @Operation(summary = "用户分配角色", operationId = "system:permission:permission:allot-user-role", description = "先删除旧关系，然后绑定新的关联关系")
    @PostMapping("/allot-user-role")
    public Result<Boolean> allotUserRole(@RequestBody @Validated AllotUserRole allotUserRole) {
        return ResultWrapper.ok(permissionService.allotUserRole(allotUserRole));
    }


    @PreAuthorize("@ss.hasPermission('system:permission:permission:allot-role-resource')")
    @Operation(summary = "角色赋予资源", operationId = "system:permission:permission:allot-role-resource", description = "注意：会删除掉旧的绑定关系！！！ 会删除掉旧的绑定关系！！！从新绑定")
    @PostMapping("/allot-role-resource")
    public Result<Boolean> allotRoleResource(@RequestBody @Validated AllotRoleResource allotUserRoleVo) {
        Boolean b = permissionService.allotRoleResource(allotUserRoleVo);
        return ResultWrapper.ok(Boolean.TRUE);
    }

}
