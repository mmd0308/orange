package com.hzqing.orange.admin.module.system.permission.common.vo.query;

import com.hzqing.orange.admin.starter.common.vo.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
@Schema(description = "用户")
public class UserPageQuery extends PageQuery {

    @Schema(description = "部门ID")
    private Long departmentId;

    @Schema(description = "是否包含子集部门用户数据[只有departmentId不为空时,生效],true: 查询该部门及子部门所有的用户数据.默认:false")
    private boolean includeSubsetDepartmentUsers;

    @Schema(description = "用户名称")
    private String name;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "登陆账号")
    private String username;

    @Schema(description = "手机号")
    private String phone;

}
