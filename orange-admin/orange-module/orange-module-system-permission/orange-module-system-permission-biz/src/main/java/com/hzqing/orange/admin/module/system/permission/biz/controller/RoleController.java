package com.hzqing.orange.admin.module.system.permission.biz.controller;


import com.hzqing.orange.admin.module.system.permission.biz.converter.RoleConverter;
import com.hzqing.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.RoleEntity;
import com.hzqing.orange.admin.module.system.permission.biz.manager.RoleManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.RoleService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.Role;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.RoleAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.RolePageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleUpdateRequest;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Tag(name = "系统权限-角色管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/role")
public class RoleController {

    private final RoleService roleService;

    private final RoleManager roleManager;


    @Operation(summary = "分页查询", operationId = "system:permission:role:page")
    @PostMapping(value = "/page")
    public Result<PageVO<Role>> page(@RequestBody RolePageQuery query) {
        PageVO<Role> result = roleService.page(query);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据参数查询所有数据", operationId = "system:permission:role:query-all")
    @PostMapping(value = "/query-all")
    public Result<List<Role>> queryAll(@RequestBody RoleAllQuery query) {
        RoleListQuery listQuery = RoleListQuery.builder().name(query.getName()).build();
        List<RoleEntity> entityList = roleManager.listByParams(listQuery);
        return ResultWrapper.ok(RoleConverter.INSTANCE.toListVo(entityList));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:permission:role:get")
    @GetMapping("/{id}")
    public Result<Role> getById(@PathVariable("id") Long id) {
        RoleEntity entity = roleManager.getById(id);
        return ResultWrapper.ok(RoleConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:role:add')")
    @Operation(summary = "新建", operationId = "system:permission:role:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated RoleAddRequest request) {
        return ResultWrapper.ok(roleService.add(request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:role:update')")
    @Operation(summary = "根据ID更新", operationId = "system:permission:role:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody RoleUpdateRequest request) {
        return ResultWrapper.ok(roleService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:role:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:permission:role:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(roleManager.removeById(id));
    }
}
