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

    @Schema(description = "角色的权限编码")
    private List<String> rolePermissionList;

    @Schema(description = "用户信息")
    private UserVO user;
}
