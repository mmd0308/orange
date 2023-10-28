package cn.hengzq.orange.admin.module.system.info.biz.controller;

import cn.hengzq.orange.admin.module.system.info.biz.convert.InfoConverter;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoManager;
import cn.hengzq.orange.admin.module.system.info.biz.service.InfoService;
import cn.hengzq.orange.admin.module.system.info.common.constant.SystemInfoConstant;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoSimpleVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoPageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.ValidatedGroupConstant;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.orange.admin.starter.common.vo.request.IdsRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * w
 *
 * @author 程序员橙子
 */
@Tag(name = "信息管理-信息管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemInfoConstant.V1_0_URL_PREFIX + "/info")
public class InfoController {

    private final InfoService service;

    private final InfoManager manager;

    @Operation(summary = "分页查询", operationId = "system:info:info:page")
    @PostMapping(value = "/page")
    public Result<PageVO<InfoSimpleVO>> page(@RequestBody InfoPageQuery queryVo) {
        PageVO<InfoSimpleVO> pageVO = service.page(queryVo);
        return ResultWrapper.ok(pageVO);
    }

    @Operation(summary = "查询所有", operationId = "system:info:info:all")
    @PostMapping(value = "/all")
    public Result<List<InfoSimpleVO>> queryAll(@RequestBody InfoAllQuery query) {
        List<InfoSimpleVO> list = service.queryAll(query);
        return ResultWrapper.ok(list);
    }

    @PreAuthorize("@ss.hasPermission('system:info:info:add')")
    @Operation(summary = "创建", operationId = "system:info:info:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated(value = ValidatedGroupConstant.ADD.class) InfoAddOrUpdateRequest request) {
        return ResultWrapper.ok(service.add(request));
    }

    @PreAuthorize("@ss.hasPermission('system:info:info:update')")
    @Operation(summary = "根据ID修改", operationId = "system:info:info:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody InfoAddOrUpdateRequest request) {
        return ResultWrapper.ok(service.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:info:info:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:info:info:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(service.removeById(id));
    }

    @PreAuthorize("@ss.hasPermission('system:info:info:batch-delete')")
    @Operation(summary = "根据ID批量删除", operationId = "system:info:info:batch-delete")
    @PostMapping("/batch-delete")
    public Result<Boolean> batchDelete(@RequestBody IdsRequest request) {
        return ResultWrapper.ok(manager.removeByIds(request.getIds()));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:info:info:get")
    @GetMapping("/{id}")
    public Result<InfoVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(InfoConverter.INSTANCE.toVo(manager.getById(id)));
    }

}
