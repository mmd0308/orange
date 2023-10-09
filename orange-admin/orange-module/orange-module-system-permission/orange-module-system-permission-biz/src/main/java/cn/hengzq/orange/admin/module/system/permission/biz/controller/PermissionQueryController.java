package cn.hengzq.orange.admin.module.system.permission.biz.controller;

import cn.hengzq.orange.admin.module.system.permission.biz.service.PermissionService;
import cn.hengzq.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourceIds;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RouterTreeVO;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Tag(name = "系统权限-权限查询相关接口")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/permission")
public class PermissionQueryController {

    private final PermissionService permissionService;

    @Operation(summary = "查询用户角色ID", operationId = "system:permission:permission:queryRoleIdByUserId", description = "根据用户ID获取该用户拥有的角色")
    @GetMapping("/queryRoleIdByUserId/{userId}")
    public Result<List<Long>> queryRoleIdByUserId(@PathVariable("userId") Long userId) {
        List<Long> list = permissionService.queryRoleIdByUserId(userId);
        return ResultWrapper.ok(list);
    }

    @Operation(summary = "根据角色ID查询资源ID", operationId = "system:permission:permission:query_resource_ids")
    @GetMapping("/query_resource_ids/{roleId}")
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
