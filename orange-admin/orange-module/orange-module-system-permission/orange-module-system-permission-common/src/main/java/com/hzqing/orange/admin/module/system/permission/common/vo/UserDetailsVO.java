package com.hzqing.orange.admin.module.system.permission.common.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "用户-详情")
public class UserDetailsVO extends UserVO {

    @Schema(description = "用户拥有角色")
    private List<RoleVO> roleVOList;

}
