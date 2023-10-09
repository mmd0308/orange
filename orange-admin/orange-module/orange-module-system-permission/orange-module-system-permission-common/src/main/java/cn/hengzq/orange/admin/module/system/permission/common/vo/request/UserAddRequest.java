package cn.hengzq.orange.admin.module.system.permission.common.vo.request;

import cn.hengzq.orange.admin.module.system.permission.common.constants.enums.UserSexEnum;
import cn.hengzq.orange.admin.module.system.permission.common.constants.exception.UserErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;


/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "用户-新增")
public class UserAddRequest implements Serializable {

    @Schema(description = "部门ID")
    private Long departmentId;

    @Schema(description = "头像 存储文件ID")
    private Long avatar;

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "用户性别")
    private UserSexEnum sex;

    @Schema(description = "手机号")
    private String phone;

    @NotBlank(message = UserErrorCode.USER_USERNAME_CANNOT_NULL_KEY)
    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "备注")
    private String remark;

}
