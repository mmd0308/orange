package com.hzqing.orange.admin.module.system.permission.biz.controller;

import com.hzqing.orange.admin.module.system.permission.biz.converter.MenuConverter;
import com.hzqing.orange.admin.module.system.permission.biz.manager.MenuManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.MenuService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionConstants;
import com.hzqing.orange.admin.module.system.permission.common.vo.Menu;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuButtonTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;
import com.hzqing.orange.admin.starter.common.result.Result;
import com.hzqing.orange.admin.starter.common.result.ResultWrapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Tag(name = "系统权限-菜单管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstants.V1_0_URL_PREFIX + "/menu")
public class MenuController {

    private final MenuService menuService;

    private final MenuManager menuManager;

    @PreAuthorize("@ss.hasPermission('system:permission:menu:add')")
    @Operation(summary = "新建", operationId = "system:permission:menu:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated MenuAddRequest request) {
        return ResultWrapper.ok(menuService.add(request));
    }


    @PreAuthorize("@ss.hasPermission('system:permission:menu:update')")
    @Operation(summary = "根据ID更新", operationId = "system:permission:menu:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody MenuUpdateRequest request) {
        return ResultWrapper.ok(menuService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:menu:delete')")
    @Operation(summary = "根据ID删除", description = "如果存在子集和按钮，不允许删除", operationId = "system:permission:menu:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(menuService.removeById(id));
    }

    @PostMapping("/query-tree")
    @Operation(summary = "树型结构数据", operationId = "system:permission:menu:query-tree", description = "返回所有的数据")
    public Result<List<MenuTree>> queryTree(@RequestBody MenuTreeQuery query) {
        return ResultWrapper.ok(menuService.queryTree(query));
    }

    @GetMapping("/query-all-menu-and-button-tree")
    @Operation(summary = "查询全部菜单和按钮数据", operationId = "system:permission:menu:query-all-menu-and-button-tree", description = "返回菜单和按钮组合的树型结构全部数据")
    public Result<List<MenuButtonTree>> queryAllMenuAndButtonTree() {
        return ResultWrapper.ok(menuService.queryAllMenuAndButtonTree());
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:permission:menu:get")
    @GetMapping("/{id}")
    public Result<Menu> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(MenuConverter.INSTANCE.toVo(menuManager.getById(id)));
    }

}
