package cn.hengzq.orange.admin.module.system.file.biz.controller;

import cn.hengzq.orange.admin.module.system.file.biz.convert.FileConfigConverter;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileConfigManager;
import cn.hengzq.orange.admin.module.system.file.biz.service.FileConfigService;
import cn.hengzq.orange.admin.module.system.file.common.constant.SystemFileConstant;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileConfigVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileStorageVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigPageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
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
@Tag(name = "文件管理-文件配置管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemFileConstant.V1_0_URL_PREFIX + "/config")
public class FileConfigController {

    private final FileConfigService service;

    private final FileConfigManager manager;

    @Operation(summary = "分页查询", operationId = "system:file:config:page")
    @PostMapping(value = "/page")
    public Result<PageVO<FileConfigVO>> page(@RequestBody FileConfigPageQuery query) {
        PageVO<FileConfigVO> page = service.page(query);
        return ResultWrapper.ok(page);
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:file:config:get")
    @GetMapping("/{id}")
    public Result<FileConfigVO> getById(@PathVariable("id") Long id) {
        FileConfigEntity entity = manager.getById(id);
        return ResultWrapper.ok(FileConfigConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:file:config:add')")
    @Operation(summary = "创建", operationId = "system:file:config:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated FileConfigAddOrUpdateRequest request) {
        Long id = service.add(request);
        return ResultWrapper.ok(id);
    }

    @PreAuthorize("@ss.hasPermission('system:file:config:update')")
    @Operation(summary = "根据ID修改", operationId = "system:file:config:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody FileConfigAddOrUpdateRequest request) {
//        fileConfigManager.updateById(id, fileConfigVo)
        return ResultWrapper.ok();
    }

    @PreAuthorize("@ss.hasPermission('system:file:config:update-master')")
    @Operation(summary = "根据ID更新为主节点", operationId = "system:file:config:update-master")
    @PutMapping("/master/{id}")
    public Result<Boolean> updateMaster(@PathVariable("id") Long id) {
        return ResultWrapper.ok(service.updateMaster(id));
    }

    @PreAuthorize("@ss.hasPermission('system:file:config:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:file:config:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(service.removeById(id));
    }

    @Operation(summary = "查询全部的存储器", operationId = "system:file:config:query-all-storages")
    @GetMapping("/query-all-storages")
    public Result<List<FileStorageVO>> queryAllStorages() {
        List<FileStorageVO> storageTypeList = service.queryAllStorages();
        return ResultWrapper.ok(storageTypeList);
    }

}
