package com.hzqing.orange.admin.module.system.dict.common.vo;

import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "字典数据")
@Data
public class DictDataVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "排序")
    private Integer sort;

    @NotBlank(message = "字典标签不能为空")
    @Schema(description = "字典数据标签")
    private String dictLabel;

    @NotNull(message = "字典数据键值不能为空")
    @Schema(description = "字典数据键值")
    private String dictValue;

    @NotBlank(message = "字典类型不能为空")
    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态")
    private CommonDataStatusEnum status;

    @Schema(description = "预设标记")
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "回显样式")
    private String showStyle;

    @Schema(description = "备注")
    private String remark;

}
