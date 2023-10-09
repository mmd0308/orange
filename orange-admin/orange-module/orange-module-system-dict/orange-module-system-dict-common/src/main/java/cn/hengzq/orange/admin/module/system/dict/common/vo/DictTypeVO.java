package cn.hengzq.orange.admin.module.system.dict.common.vo;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonDataStatusEnum;
import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DictTypeVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "字典名称")
    private String name;

    @Schema(description = "字典类型")
    private String dictType;

    @Schema(description = "状态")
    private CommonDataStatusEnum status;

    @Schema(description = "预设标记")
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "备注")
    private String remark;
}
