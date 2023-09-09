package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.module.system.permission.common.constants.enums.ResourceTypeEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色资源关系
 *
 *@author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("sys_role_resource_rl")
public class RoleResourceRlEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("resource_id")
    private Long resourceId;

    @TableField("resource_type")
    private ResourceTypeEnum resourceType;
}
