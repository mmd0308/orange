package com.hzqing.orange.admin.module.system.permission.biz.controller;


import com.hzqing.orange.admin.module.system.permission.biz.converter.DepartmentConverter;
import com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import com.hzqing.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.DepartmentService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.DepartmentVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
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
 * @author 程序员橙子
 */
@Tag(name = "系统权限-部门管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentManager departmentManager;

    @PreAuthorize("@ss.hasPermission('system:permission:department:add')")
    @Operation(summary = "新建", operationId = "system:permission:department:add")
    @PostMapping
    public Result<Long> add(@RequestBody DepartmentVO departmentVO) {
        return ResultWrapper.ok(departmentService.add(departmentVO));
    }

    @Operation(summary = "根据ID查询", operationId = "system:permission:department:get")
    @GetMapping("/{id}")
    public Result<DepartmentVO> getById(@PathVariable("id") Long id) {
        DepartmentEntity entity = departmentManager.getById(id);
        return ResultWrapper.ok(DepartmentConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:department:update')")
    @Operation(summary = "根据ID更新", operationId = "system:permission:department:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody DepartmentUpdateRequest request) {
        return ResultWrapper.ok(departmentService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:department:delete')")
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

    @PostMapping(value = "/query-all")
    @Operation(summary = "查询所有的数据", operationId = "system:permission:department:query-all", description = "返回所有的数据")
    public Result<List<DepartmentVO>> queryAll(@RequestBody DepartmentAllQuery query) {
        List<DepartmentVO> list = departmentService.queryAll(query);
        return ResultWrapper.ok(list);
    }

    @Operation(summary = "根据ID查询自身及子集部门数据", description = "返回数据包含本部及子部门数据", operationId = "system:permission:department:query-self-and-subset-by-id")
    @GetMapping("/query-self-and-subset-by-id/{id}")
    public Result<List<DepartmentVO>> querySelfAndSubsetById(@PathVariable("id") Long id) {
        List<DepartmentVO> result = departmentService.querySelfAndSubsetById(id);
        return ResultWrapper.ok(result);
    }
}
