package com.hzqing.orange.admin.module.system.permission.biz.controller;


import  com.hzqing.orange.admin.module.system.permission.biz.converter.DepartmentConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import  com.hzqing.orange.admin.module.system.permission.biz.service.DepartmentService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.Department;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.DepartmentUpdateRequest;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-部门管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentManager departmentManager;

    @PreAuthorize("@ps.hasPermission('system:permission:department:add')")
    @Operation(summary = "新建", operationId = "system:permission:department:add")
    @PostMapping
    public Result<Long> add(@RequestBody Department department) {
        return ResultWrapper.ok(departmentService.add(department));
    }

    @Operation(summary = "根据ID查询", operationId = "system:permission:department:get")
    @GetMapping("/{id}")
    public Result<Department> getById(@PathVariable("id") Long id) {
        DepartmentEntity entity = departmentManager.getById(id);
        return ResultWrapper.ok(DepartmentConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ps.hasPermission('system:permission:department:update')")
    @Operation(summary = "根据ID更新", operationId = "system:permission:department:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody DepartmentUpdateRequest request) {
        return ResultWrapper.ok(departmentService.updateById(id, request));
    }

    @PreAuthorize("@ps.hasPermission('system:permission:department:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:permission:department:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(departmentService.removeById(id));
    }

    @PostMapping(value = "/query-tree")
    @Operation(summary = "树型结构数据", operationId = "system:permission:department:query-tree", description = "返回所有的数据")
    public Result<List<DepartmentTree>> queryTree(@RequestBody DepartmentTreeQuery query) {
        List<DepartmentTree> treeVoList = departmentService.queryTree(query);
        return ResultWrapper.ok(treeVoList);
    }

    @Operation(summary = "根据ID查询自身及子集部门数据", description = "返回数据包含本部及子部门数据", operationId = "system:permission:department:query-self-and-subset-by-id")
    @GetMapping("/query-self-and-subset-by-id/{id}")
    public Result<List<Department>> querySelfAndSubsetById(@PathVariable("id") Long id) {
        List<Department> result = departmentService.querySelfAndSubsetById(id);
        return ResultWrapper.ok(result);
    }
}
