package cn.hengzq.orange.admin.module.system.info.biz.controller;

import cn.hengzq.orange.admin.module.system.info.biz.convert.InfoTypeConverter;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoTypeManager;
import cn.hengzq.orange.admin.module.system.info.biz.service.InfoTypeService;
import cn.hengzq.orange.admin.module.system.info.common.constant.SystemInfoConstant;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoTypeVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypeAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypePageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.ValidatedGroupConstant;
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

/**w
 * @author 程序员橙子
 */
@Tag(name = "信息管理-类型管理")
@RestController
@AllArgsConstructor
@RequestMapping(SystemInfoConstant.V1_0_URL_PREFIX + "/type")
public class InfoTypeController {

    private final InfoTypeService typeService;

    private final InfoTypeManager typeManager;

    @Operation(summary = "分页查询", operationId = "system:info:type:page")
    @PostMapping(value = "/page")
    public Result<PageVO<InfoTypeVO>> page(@RequestBody InfoTypePageQuery queryVo) {
        PageVO<InfoTypeVO> pageVO = typeService.page(queryVo);
        return ResultWrapper.ok(pageVO);
    }

    @Operation(summary = "查询所有", operationId = "system:info:type:all")
    @PostMapping(value = "/all")
    public Result<List<InfoTypeVO>> queryAll(@RequestBody InfoTypeAllQuery query) {
        List<InfoTypeVO> list = typeService.queryAll(query);
        return ResultWrapper.ok(list);
    }

    @PreAuthorize("@ss.hasPermission('system:info:type:add')")
    @Operation(summary = "创建类型", operationId = "system:info:type:add")
    @PostMapping
    public Result<Long> add(@RequestBody @Validated(value = ValidatedGroupConstant.ADD.class) InfoTypeAddOrUpdateRequest request) {
        return ResultWrapper.ok(typeService.add(request));
    }

    @PreAuthorize("@ss.hasPermission('system:info:type:update')")
    @Operation(summary = "根据ID修改", operationId = "system:info:type:update")
    @PutMapping("/{id}")
    public Result<Boolean> update(@PathVariable("id") Long id, @RequestBody InfoTypeAddOrUpdateRequest request) {
        return ResultWrapper.ok(typeService.updateById(id, request));
    }

    @PreAuthorize("@ss.hasPermission('system:info:type:delete')")
    @Operation(summary = "根据ID删除", operationId = "system:info:type:delete")
    @DeleteMapping("/{id}")
    public Result<Boolean> removeById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(typeService.removeById(id));
    }

    @Operation(summary = "根据ID查询详情", operationId = "system:info:type:get")
    @GetMapping("/{id}")
    public Result<InfoTypeVO> getById(@PathVariable("id") Long id) {
        return ResultWrapper.ok(InfoTypeConverter.INSTANCE.toVo(typeManager.getById(id)));
    }

}
