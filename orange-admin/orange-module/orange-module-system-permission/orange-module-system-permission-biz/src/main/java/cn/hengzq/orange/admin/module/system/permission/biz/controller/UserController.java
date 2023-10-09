package cn.hengzq.orange.admin.module.system.permission.biz.controller;

import cn.hengzq.orange.admin.module.system.permission.biz.converter.UserConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.UserManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.constant.SystemPermissionConstant;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserDetailsVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ResetPasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UpdatePasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserUpdateRequest;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.orange.admin.starter.common.vo.request.IdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Tag(name = "系统权限-用户管理")
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(SystemPermissionConstant.V1_0_URL_PREFIX + "/user")
public class UserController {

    private final UserService userService;

    private final UserManager userManager;

    @Operation(summary = "分页查询", operationId = "system:permission:user:page")
    @PostMapping(value = "/page")
    public Result<PageVO<UserVO>> page(@RequestBody UserPageQuery query) {
        PageVO<UserVO> result = userService.page(query);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "查询所有数据", operationId = "system:permission:user:all")
    @PostMapping(value = "/all")
    public Result<List<UserVO>> queryAll(@RequestBody UserAllQuery query) {
        List<UserVO> result = userService.queryAll(query);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据ID查询", operationId = "system:permission:user:get")
    @GetMapping("/{id}")
    public Result<UserVO> getById(@PathVariable("id") Long id) {
        UserEntity entity = userManager.getById(id);
        return ResultWrapper.ok(UserConverter.INSTANCE.toVo(entity));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:permission:user:get-details")
    @GetMapping("/details/{id}")
    public Result<UserDetailsVO> getDetailsById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(userService.getDetailsById(id));
    }

    @Operation(summary = "根据多个ID查询", operationId = "system:permission:user:query-by-ids")
    @PostMapping("/query-by-ids")
    public Result<List<UserVO>> queryByIds(@RequestBody IdsRequest request) {
        List<UserEntity> entityList = userManager.listByIds(request.getIds());
        return ResultWrapper.ok(UserConverter.INSTANCE.toListVo(entityList));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:user:add')")
    @Operation(summary = "创建用户", operationId = "system:permission:user:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated UserAddRequest request) {
        return ResultWrapper.ok(userService.add(request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:user:update')")
    @Operation(summary = "根据ID更新", operationId = "system:permission:user:update")
    @PutMapping("/{id}")
    public Result<Boolean> updateById(@PathVariable("id") Long id, @RequestBody UserUpdateRequest user) {
        return ResultWrapper.ok(userManager.updateById(id, UserConverter.INSTANCE.toEntity(user)));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:user:update-password')")
    @Operation(summary = "修改密码", operationId = "system:permission:user:update-password")
    @PutMapping("/update-password")
    public Result<Boolean> updatePassword(@RequestBody UpdatePasswordRequest request) {
        return ResultWrapper.ok(userService.updatePassword(request));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:user:reset-password')")
    @Operation(summary = "重置密码", operationId = "system:permission:user:reset-password")
    @PutMapping("/reset-password")
    public Result<Boolean> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResultWrapper.ok(userService.resetPassword(request));
    }


    @PreAuthorize("@ss.hasPermission('system:permission:user:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:permission:user:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(userManager.removeById(id));
    }

    @PreAuthorize("@ss.hasPermission('system:permission:user:batch-delete')")
    @Operation(summary = "根据ID批量删除", operationId = "system:permission:user:batch-delete")
    @PostMapping("/batch-delete")
    public Result<Boolean> batchDelete(@RequestBody IdsRequest request) {
        return ResultWrapper.ok(userManager.removeByIds(request.getIds()));
    }

}
