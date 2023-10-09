package cn.hengzq.orange.admin.module.system.dict.biz.controller;

import cn.hengzq.orange.admin.module.system.dict.biz.converter.DictDataConverter;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import cn.hengzq.orange.admin.module.system.dict.biz.manager.DictDataManager;
import cn.hengzq.orange.admin.module.system.dict.biz.service.DictDataService;
import cn.hengzq.orange.admin.module.system.dict.common.constants.SystemDictConstants;
import cn.hengzq.orange.admin.module.system.dict.common.vo.DictDataVO;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictDataAllQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictDataPageQuery;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Tag(name = "字典-数据管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemDictConstants.V1_0_URL_PREFIX + "/dict-data")
public class DictDataController {

    private final DictDataService dictDataService;
    private final DictDataManager dictDataManager;

    @Operation(summary = "分页查询", operationId = "system:dict:dict-data:page")
    @PostMapping(value = "/page")
    public Result<PageVO<DictDataVO>> page(@RequestBody DictDataPageQuery query) {
        PageVO<DictDataVO> result = dictDataService.page(query);
        return ResultWrapper.ok(result);
    }

    @PreAuthorize("@ss.hasPermission('system:dict:dict-data:add')")
    @Operation(summary = "新建", operationId = "system:dict:dict-data:add")
    @PostMapping
    public Result<Long> save(@Validated @RequestBody DictDataVO dictDataVO) {
        return ResultWrapper.ok(dictDataManager.add(DictDataConverter.INSTANCE.toEntity(dictDataVO)));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:dict:dict-data:get")
    @GetMapping("/{id}")
    public Result<DictDataVO> getById(@PathVariable("id") Long id) {
        DictDataEntity entity = dictDataManager.getById(id);
        return ResultWrapper.ok(DictDataConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:dict:dict-data:update')")
    @Operation(summary = "根据ID修改", operationId = "system:dict:dict-data:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@Parameter(description = "主键ID") @PathVariable("id") Long id, @RequestBody DictDataVO dictDataVO) {
        return ResultWrapper.ok(dictDataManager.updateById(id, DictDataConverter.INSTANCE.toEntity(dictDataVO)));
    }

    @PreAuthorize("@ss.hasPermission('system:dict:dict-data:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:dict:dict-data:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@Parameter(description = "主键ID") @PathVariable("id") Long id) {
        return ResultWrapper.ok(dictDataManager.removeById(id));
    }

    @Operation(summary = "根据字典类型获取数据", operationId = "system:dict:dict-data:query-by-type")
    @GetMapping("/query-by-type/{dictType}")
    public Result<List<DictDataVO>> queryByType(@PathVariable String dictType) {
        List<DictDataEntity> entityList = dictDataManager.listByType(dictType);
        return ResultWrapper.ok(DictDataConverter.INSTANCE.toListVo(entityList));
    }

    @Operation(summary = "根据参数查询所有数据", operationId = "system:dict:dict-data:all")
    @PostMapping(value = "/all")
    public Result<List<DictDataVO>> queryAll(@RequestBody DictDataAllQuery query) {
        DictDataListQuery listQuery = DictDataConverter.INSTANCE.toListQuery(query);
        List<DictDataEntity> entityList = dictDataManager.listByParams(listQuery);
        return ResultWrapper.ok(DictDataConverter.INSTANCE.toListVo(entityList));
    }

}
