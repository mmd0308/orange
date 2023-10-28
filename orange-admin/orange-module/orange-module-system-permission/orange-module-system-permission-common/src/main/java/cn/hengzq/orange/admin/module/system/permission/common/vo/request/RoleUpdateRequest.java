package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import cn.hengzq.orange.admin.starter.common.enums.support.CommonDataEnableStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "角色更新参数")
@Data
public class RoleUpdateRequest implements Serializable {

    @Schema(description = "角色名称")
    private String name;

    @Schema(description = "角色权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private CommonDataEnableStatusEnum status;

    @Schema(description = "备注")
    private String remark;

}
