package com.hzqing.orange.admin.module.system.permission.biz.controller;

import com.hzqing.orange.admin.module.system.permission.biz.service.PermissionService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.ResourceIds;
import com.hzqing.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import com.hzqing.orange.admin.module.system.permission.common.vo.RouterTreeVO;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-权限查询相关接口")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/permission")
public class PermissionQueryController {

    private final PermissionService permissionService;

    @Operation(summary = "查询用户角色ID", operationId = "system:permission:permission:query-role-id-by-user-id", description = "根据用户ID获取该用户拥有的角色")
    @GetMapping("/query-role-id-by-user-id/{userId}")
    public Result<List<Long>> queryRoleIdByUserId(@PathVariable("userId") Long userId) {
        List<Long> list = permissionService.queryRoleIdByUserId(userId);
        return ResultWrapper.ok(list);
    }

    @Operation(summary = "根据角色ID查询资源", operationId = "system:permission:permission:query-resource-ids-by-role-id")
    @GetMapping("/query-resource-ids-by-role-id/{roleId}")
    public Result<ResourceIds> queryResourceIdsByRoleId(@PathVariable("roleId") Long roleId) {
        return ResultWrapper.ok(permissionService.queryResourceIdsByRoleId(roleId));
    }

    @Operation(summary = "查询用户路由", operationId = "system:permission:permission:query-current-user-routers-tree", description = "只返回当前登录用户拥有的路由")
    @GetMapping("/query-current-user-routers-tree")
    public Result<List<RouterTreeVO>> queryCurrentUserRoutersTree() {
        return ResultWrapper.ok(permissionService.queryCurrentUserRoutersTree());
    }

    @Operation(summary = "获取当前登录用户的所有权限标识", operationId = "system:permission:permission:query-current-user-permissions")
    @GetMapping("/query-current-user-permissions")
    public Result<ResourcePermissions> queryCurrentUserPermissions() {
        Long userId = GlobalContextHelper.getCurrentUserId();
        return ResultWrapper.ok(permissionService.queryAllPermissionsByUserId(userId));
    }

}
