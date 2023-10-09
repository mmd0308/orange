package cn.hengzq.orange.admin.module.system.permission.biz.controller;

import cn.hengzq.orange.admin.module.system.permission.biz.converter.MenuConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.MenuManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.MenuService;
import cn.hengzq.orange.admin.module.system.permission.common.constant.SystemPermissionConstant;
import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;
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
@Tag(name = "系统权限-菜单管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstant.V1_0_URL_PREFIX + "/menu")
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

    @PostMapping("/all")
    @Operation(summary = "查询所有数据-列表", operationId = "system:permission:menu:all", description = "返回所有的数据")
    public Result<List<MenuVO>> queryAll(@RequestBody MenuAllQuery query) {
        return ResultWrapper.ok(menuService.queryByParams(query));
    }

    @PostMapping("/tree")
    @Operation(summary = "查询所有数据-树型结构", operationId = "system:permission:menu:tree", description = "返回所有的数据")
    public Result<List<MenuTreeVO>> queryTree(@RequestBody MenuTreeQuery query) {
        return ResultWrapper.ok(menuService.queryTree(query));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:permission:menu:get")
    @GetMapping("/{id}")
    public Result<MenuVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(MenuConverter.INSTANCE.toVo(menuManager.getById(id)));
    }

}
