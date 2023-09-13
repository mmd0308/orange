package com.hzqing.orange.admin.module.system.permission.common.vo.request;

import com.hzqing.orange.admin.module.system.permission.common.constants.enums.UserSexEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "用户-新增")
public class UserUpdateRequest implements Serializable {

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

    @Schema(description = "备注")
    private String remark;

}
