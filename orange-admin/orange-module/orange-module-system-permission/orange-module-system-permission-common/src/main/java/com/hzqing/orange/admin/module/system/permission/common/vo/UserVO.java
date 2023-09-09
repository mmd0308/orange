package com.hzqing.orange.admin.module.system.permission.common.vo;

import com.hzqing.orange.admin.module.system.permission.common.constants.enums.UserSexEnum;
import com.hzqing.orange.admin.starter.common.vo.BaseTenantVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户")
public class UserVO extends BaseTenantVO {

    @Schema(description = "主键", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

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

    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "备注")
    private String remark;

}
