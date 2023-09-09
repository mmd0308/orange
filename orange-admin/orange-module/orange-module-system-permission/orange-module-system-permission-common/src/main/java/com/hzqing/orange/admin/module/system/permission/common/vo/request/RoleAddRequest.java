package com.hzqing.orange.admin.module.system.permission.common.vo.request;

import com.hzqing.orange.admin.module.system.permission.common.constants.exception.RoleErrorCode;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "角色新增参数")
@Data
public class RoleAddRequest implements Serializable {

    @NotNull(message = RoleErrorCode.ROLE_NAME_CANNOT_NULL_KEY)
    @Schema(description = "角色名称")
    private String name;

    @NotNull(message = RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL_KEY)
    @Schema(description = "角色权限编码")
    private String permission;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "状态")
    private CommonDataStatusEnum status = CommonDataStatusEnum.NORMAL;

    @Schema(description = "备注")
    private String remark;

}