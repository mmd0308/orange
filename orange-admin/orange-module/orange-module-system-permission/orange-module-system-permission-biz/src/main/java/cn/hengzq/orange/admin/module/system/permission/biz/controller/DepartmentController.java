package cn.hengzq.orange.admin.module.system.permission.biz.controller;


import cn.hengzq.orange.admin.module.system.permission.biz.converter.DepartmentConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.DepartmentService;
import cn.hengzq.orange.admin.module.system.permission.common.constant.SystemPermissionConstant;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.DepartmentVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.DepartmentTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.DepartmentAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
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
@Tag(name = "系统权限-部门管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstant.V1_0_URL_PREFIX + "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentManager departmentManager;

    @PreAuthorize("@ss.hasPermission('system:permission:department:add')")
    @Operation(summary = "新建", operationId = "system:permission:department:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated DepartmentAddOrUpdateRequest request) {
        return ResultWrapper.ok(departmentService.add(request));
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
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody @Validated DepartmentAddOrUpdateRequest request) {
        return ResultWrapper.ok(departmentService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:department:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:permission:department:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(departmentService.removeById(id));
    }

    @PostMapping(value = "/tree")
    @Operation(summary = "树型结构数据", operationId = "system:permission:department:tree", description = "返回所有的数据")
    public Result<List<DepartmentTreeVO>> queryTree(@RequestBody DepartmentTreeQuery query) {
        List<DepartmentTreeVO> treeVoList = departmentService.queryTree(query);
        return ResultWrapper.ok(treeVoList);
    }

    @PostMapping(value = "/all")
    @Operation(summary = "查询所有的数据", operationId = "system:permission:department:all", description = "返回所有的数据")
    public Result<List<DepartmentVO>> queryAll(@RequestBody DepartmentAllQuery query) {
        List<DepartmentVO> list = departmentService.queryAll(query);
        return ResultWrapper.ok(list);
    }

    @Operation(summary = "根据ID查询自身及子集部门数据", description = "返回数据包含本部及所有子部门数据", operationId = "system:permission:department:query-self-and-subset")
    @GetMapping("/query-self-and-subset/{id}")
    public Result<List<DepartmentVO>> querySelfAndSubsetById(@PathVariable("id") Long id) {
        List<DepartmentVO> result = departmentService.querySelfAndSubsetById(id);
        return ResultWrapper.ok(result);
    }
}
