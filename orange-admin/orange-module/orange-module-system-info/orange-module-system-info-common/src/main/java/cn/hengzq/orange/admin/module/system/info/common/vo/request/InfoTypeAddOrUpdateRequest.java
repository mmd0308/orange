package cn.hengzq.orange.admin.module.system.info.common.vo.request;

import cn.hengzq.orange.admin.module.system.info.common.exception.SystemInfoErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.ValidatedGroupConstant;
import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 类型
 *
 * @author 程序员橙子
 */
@Schema(description = "消息类型管理-新增或修改参数")
@Data
public class InfoTypeAddOrUpdateRequest implements Serializable {

    @Schema(description = "parentId")
    private Long parentId;

    @NotBlank(groups = {ValidatedGroupConstant.ADD.class}, message = SystemInfoErrorCode.GLOBAL_PARAMETER_NAME_CANNOT_NULL_KEY)
    @Schema(description = "类型名称")
    private String name;

    @NotBlank(groups = {ValidatedGroupConstant.ADD.class}, message = SystemInfoErrorCode.TYPE_CODE_CANNOT_NULL_KEY)
    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
