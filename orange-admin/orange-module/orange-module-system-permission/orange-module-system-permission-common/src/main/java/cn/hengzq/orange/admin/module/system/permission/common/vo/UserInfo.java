package cn.hengzq.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 程序员橙子
 */
@Data
@Builder
@Schema(description = "用户权限信息")
public class UserInfo implements Serializable {

    @Schema(description = "用户信息")
    private UserVO user;

    @Schema(description = "角色权限标识")
    private List<String> rolePermissions;

    @Schema(description = "菜单权限标识")
    private List<String> menuPermissions;

    @Schema(description = "按钮权限标识")
    private List<String> buttonPermissions;

}
