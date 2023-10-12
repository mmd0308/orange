package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import cn.hengzq.orange.admin.module.system.permission.common.exception.support.MenuErrorCode;
import cn.hengzq.orange.admin.starter.common.constant.ValidatedGroupConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 程序员橙子
 */
@Schema(description = "菜单管理-新增参数")
@Data
public class MenuAddOrUpdateRequest implements Serializable {

    @Schema(description = "父级别ID")
    private Long parentId;

    @NotNull(groups = {ValidatedGroupConstant.ADD.class}, message = MenuErrorCode.MENU_NAME_CANNOT_NULL_KEY)
    @Schema(description = "菜单名称")
    private String name;

    @NotNull(groups = {ValidatedGroupConstant.ADD.class}, message = MenuErrorCode.MENU_PERMISSION_CANNOT_NULL_KEY)
    @Schema(description = "权限编码")
    private String permission;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "是否隐藏 true：隐藏 false：不隐藏")
    private boolean hidden;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "备注")
    @Size(max = 500, message = "长度必须小于等于500")
    private String remark;
}
