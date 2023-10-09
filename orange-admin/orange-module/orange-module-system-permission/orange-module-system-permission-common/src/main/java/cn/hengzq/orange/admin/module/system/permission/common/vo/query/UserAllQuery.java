package cn.hengzq.orange.admin.module.system.permission.common.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "用户-查询全部参数")
public class UserAllQuery implements Serializable {

    @Schema(description = "部门ID")
    private Long departmentId;

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "手机号")
    private String phone;

}
