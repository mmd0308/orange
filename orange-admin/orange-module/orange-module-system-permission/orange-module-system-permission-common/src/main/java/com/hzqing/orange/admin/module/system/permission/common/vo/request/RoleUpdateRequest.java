package com.hzqing.orange.admin.module.system.permission.common.vo.request;

import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
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
    private CommonDataStatusEnum status;

    @Schema(description = "备注")
    private String remark;

}
