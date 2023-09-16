package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import com.hzqing.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户")
public class UserPageQuery extends PageQuery {

    @Schema(description = "部门ID 包含子集部门用户数据")
    private Long departmentId;

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "用户名称,模糊擦查询")
    private String nameLike;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "登陆账号,模糊查询")
    private String usernameLike;

    @Schema(description = "手机号")
    private String phone;

}
