package cn.hengzq.orange.admin.module.system.info.common.vo;

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
@Schema(description = "信息管理-缩减版")
@Data
public class InfoSimpleVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "类型ID")
    private Long typeId;

    @Schema(description = "类型名称")
    private String typeName;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "排序")
    private Integer sort;

}
