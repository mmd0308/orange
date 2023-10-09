package cn.hengzq.orange.admin.module.system.record.biz.controller;

import cn.hengzq.orange.admin.module.system.record.biz.converter.RecordOperationConverter;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import cn.hengzq.orange.admin.module.system.record.biz.manager.RecordOperationManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.record.biz.service.RecordOperationService;
import cn.hengzq.orange.admin.module.system.record.common.constant.SystemRecordConstants;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordExportQuery;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author 程序员橙子
 */
@Tag(name = "记录-操作记录管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemRecordConstants.V1_0_URL_PREFIX + "/record-operation")
public class RecordOperationController {

    private final RecordOperationService operationRecordService;

    private final RecordOperationManager operationRecordManager;


    @Operation(summary = "分页查询", operationId = "system:record:operation:page")
    @PostMapping(value = "/page")
    public Result<PageVO<RecordOperationVO>> page(@RequestBody OperationRecordPageQuery queryVo) {
        Page<RecordOperationEntity> page = operationRecordManager.page(queryVo);
        PageVO<RecordOperationVO> result = RecordOperationConverter.INSTANCE.toPage(page);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "导出", operationId = "system:record:operation:export")
    @PostMapping(value = "/export")
    public Result<Void> export(@RequestBody OperationRecordExportQuery query) {
//        List<OperationRecord> exportList = operationRecordService.queryExportList(query);
        return ResultWrapper.ok();
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:record:operation:get")
    @GetMapping("/{id}")
    public Result<RecordOperationVO> getById(@PathVariable("id") Long id) {
        RecordOperationEntity entity = operationRecordManager.getById(id);
        return ResultWrapper.ok(RecordOperationConverter.INSTANCE.toVo(entity));
    }

    @PreAuthorize("@ss.hasPermission('system:record:operation:add')")
    @Operation(summary = "新建", operationId = "system:record:operation:add")
    @PostMapping
    public Result<Long> save(@Validated @RequestBody RecordOperationVO recordOperationVO) {
        return ResultWrapper.ok(operationRecordManager.add(RecordOperationConverter.INSTANCE.toEntity(recordOperationVO)));
    }

    @PreAuthorize("@ss.hasPermission('system:record:operation:clear')")
    @Operation(summary = "清空操作日志", operationId = "system:record:operation:clear")
    @DeleteMapping("/clear")
    public Result<Void> clear() {
        operationRecordManager.clear();
        return ResultWrapper.ok();
    }
}
