package cn.hengzq.orange.admin.module.system.record.biz.controller;

import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import cn.hengzq.orange.admin.module.system.record.biz.manager.RecordLoginManager;
import cn.hengzq.orange.admin.module.system.record.biz.converter.RecordLoginConverter;
import cn.hengzq.orange.admin.module.system.record.biz.service.RecordLoginService;
import cn.hengzq.orange.admin.module.system.record.common.constant.SystemRecordConstants;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordLoginVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.RecordLoginPageQuery;
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
@Tag(name = "记录-登陆记录")
@RestController
@AllArgsConstructor
@RequestMapping(SystemRecordConstants.V1_0_URL_PREFIX + "/record-login")
public class RecordLoginController {

    private final RecordLoginService RecordLoginVOService;

    private final RecordLoginManager RecordLoginVOManager;

    @Operation(summary = "分页查询", operationId = "system:record:login:page")
    @PostMapping("/page")
    public Result<PageVO<RecordLoginVO>> page(@RequestBody RecordLoginPageQuery query) {
        PageVO<RecordLoginVO> result = RecordLoginVOService.page(query);
        return ResultWrapper.ok(result);
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:record:login:get")
    @GetMapping("/{id}")
    public Result<RecordLoginVO> getById(@PathVariable("id") Long id) {
        RecordLoginEntity entity = RecordLoginVOManager.getById(id);
        return ResultWrapper.ok(RecordLoginConverter.INSTANCE.toVo(entity));
    }


    @PreAuthorize("@ss.hasPermission('system:record:login:add')")
    @Operation(summary = "新建", operationId = "system:record:login:add")
    @PostMapping
    public Result<Long> save(@Validated @RequestBody RecordLoginVO record) {
        return ResultWrapper.ok(RecordLoginVOService.add(record));
    }

}
