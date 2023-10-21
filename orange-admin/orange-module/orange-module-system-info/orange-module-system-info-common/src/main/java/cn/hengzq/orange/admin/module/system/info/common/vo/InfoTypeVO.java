package cn.hengzq.orange.admin.module.system.info.common.vo;

import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类型
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Schema(description = "消息类型管理")
@Data
public class InfoTypeVO extends BaseTenantVO {

    @Schema(description = "按钮ID", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "parentId")
    private Long parentId;


    @Schema(description = "类型名称")
    private String name;

    @Schema(description = "类型编码")
    private String code;

    @Schema(description = "预设标记", accessMode = Schema.AccessMode.READ_ONLY)
    private DataPresetFlagEnum presetFlag;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
