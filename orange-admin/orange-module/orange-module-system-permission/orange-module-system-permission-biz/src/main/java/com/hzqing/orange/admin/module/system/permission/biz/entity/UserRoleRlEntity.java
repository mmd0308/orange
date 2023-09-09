package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 *@author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@TableName("sys_user_role_rl")
public class UserRoleRlEntity extends BaseTenantEntity {

    public UserRoleRlEntity(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;
}
