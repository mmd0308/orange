package cn.hengzq.orange.admin.module.system.file.biz.controller;

import cn.hengzq.orange.admin.module.system.file.biz.manager.FileManager;
import cn.hengzq.orange.admin.module.system.file.biz.service.FileService;
import cn.hengzq.orange.admin.module.system.file.common.constant.SystemFileConstant;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FilePageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileUploadRequest;
import cn.hengzq.orange.admin.starter.common.result.Result;
import cn.hengzq.orange.admin.starter.common.result.ResultWrapper;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 程序员橙子
 */
@Tag(name = "文件管理-文件相关接口")
@RestController
@AllArgsConstructor
@RequestMapping(SystemFileConstant.V1_0_URL_PREFIX + "/file")
public class FileController {

    private final FileService service;

    private final FileManager manager;

    @Operation(summary = "分页查询", operationId = "system:file:file:page")
    @PostMapping(value = "/page")
    public Result<PageVO<FileVO>> page(@RequestBody FilePageQuery query) {
        PageVO<FileVO> page = service.page(query);
        return ResultWrapper.ok(page);
    }

    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<FileVO> upload(@Validated FileUploadRequest request) {
        return ResultWrapper.ok(service.upload(request));
    }

    @Operation(summary = "根据ID删除")
    @DeleteMapping("/{id}")
    public Result<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResultWrapper.ok();
    }

}
