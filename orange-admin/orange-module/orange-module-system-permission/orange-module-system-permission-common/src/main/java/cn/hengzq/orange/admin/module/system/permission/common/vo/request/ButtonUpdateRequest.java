package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "按钮更新请求参数")
@Data
public class ButtonUpdateRequest implements Serializable {

    @Schema(description = "按钮名称")
    private String name;

    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    private String remark;
}
