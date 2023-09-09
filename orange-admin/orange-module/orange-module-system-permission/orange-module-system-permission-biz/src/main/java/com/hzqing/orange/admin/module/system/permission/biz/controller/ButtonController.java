package com.hzqing.orange.admin.module.system.permission.biz.controller;

import com.hzqing.orange.admin.module.system.permission.biz.converter.ButtonConverter;
import com.hzqing.orange.admin.module.system.permission.biz.manager.ButtonManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.ButtonService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.Button;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.ButtonPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonUpdateRequest;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-按钮管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/button")
public class ButtonController {

    private final ButtonService buttonService;

    private final ButtonManager buttonManager;

    @Operation(summary = "分页查询", operationId = "system:permission:button:page")
    @PostMapping(value = "/page")
    public Result<PageVO<Button>> page(@RequestBody ButtonPageQuery queryVo) {
        PageVO<Button> pageVO = buttonService.page(queryVo);
        return ResultWrapper.ok(pageVO);
    }

    @PreAuthorize("@ps.hasPermission('system:permission:button:add')")
    @Operation(summary = "创建按钮", operationId = "system:permission:button:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated ButtonAddRequest request) {
        return ResultWrapper.ok(buttonService.add(request));
    }

    @PreAuthorize("@ps.hasPermission('system:permission:button:update')")
    @Operation(summary = "根据ID修改", operationId = "system:permission:button:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody ButtonUpdateRequest request) {
        return ResultWrapper.ok(buttonService.updateById(id, request));
    }

    @PreAuthorize("@ps.hasPermission('system:permission:button:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:permission:button:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(buttonManager.removeById(id));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:permission:button:get")
    @GetMapping("/{id}")
    public Result<Button> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(ButtonConverter.INSTANCE.toVo(buttonManager.getById(id)));
    }

}