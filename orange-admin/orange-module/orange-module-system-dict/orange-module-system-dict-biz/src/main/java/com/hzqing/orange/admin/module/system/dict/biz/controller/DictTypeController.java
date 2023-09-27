package com.hzqing.orange.admin.module.system.dict.biz.controller;

import com.hzqing.orange.admin.module.system.dict.biz.converter.DictTypeConverter;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import com.hzqing.orange.admin.module.system.dict.biz.manager.DictTypeManager;
import com.hzqing.orange.admin.module.system.dict.biz.service.DictTypeService;
import com.hzqing.orange.admin.module.system.dict.common.constants.SystemDictConstants;
import com.hzqing.orange.admin.module.system.dict.common.vo.DictTypeVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictTypeAllQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
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
@Tag(name = "字典-类型管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemDictConstants.V1_0_URL_PREFIX + "/dict-type")
public class DictTypeController {

    private final DictTypeService dictTypeService;

    private final DictTypeManager dictTypeManager;

    @Operation(summary = "分页查询", operationId = "system:dict:dict-type:page")
    @PostMapping(value = "/page")
    public Result<PageVO<DictTypeVO>> page(@RequestBody DictTypePageQuery queryVo) {
        PageVO<DictTypeVO> result = dictTypeService.page(queryVo);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "查询所有数据", operationId = "system:dict:dict-type:all")
    @PostMapping(value = "/all")
    public Result<List<DictTypeVO>> queryAll(@RequestBody DictTypeAllQuery queryVo) {
        List<DictTypeVO> result = dictTypeService.queryAll(queryVo);
        return ResultWrapper.ok(result);
    }


    @PreAuthorize("@ss.hasPermission('system:dict:dict-type:add')")
    @Operation(summary = "新建", operationId = "system:dict:dict-type:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated DictTypeAddRequest request) {
        return ResultWrapper.ok(dictTypeService.add(request));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:dict:dict-type:get")
    @GetMapping("/{id}")
    public Result<DictTypeVO> getById(@PathVariable("id") Long id) {
        DictTypeEntity entity = dictTypeManager.getById(id);
        return ResultWrapper.ok(DictTypeConverter.INSTANCE.toVO(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:dict:dict-type:update')")
    @Operation(summary = "根据ID更新", operationId = "system:dict:dict-type:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody DictTypeUpdateRequest request) {
        return ResultWrapper.ok(dictTypeService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:dict:dict-type:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:dict:dict-type:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(dictTypeService.removeById(id));
    }
}
