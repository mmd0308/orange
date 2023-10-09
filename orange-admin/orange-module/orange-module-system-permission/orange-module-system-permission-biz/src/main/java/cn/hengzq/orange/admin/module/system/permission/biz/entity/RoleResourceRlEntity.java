package cn.hengzq.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hengzq.orange.admin.module.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 角色资源关系
 *
 * @author 程序员橙子
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_role_resource_rl")
public class RoleResourceRlEntity extends BaseTenantEntity {

    public RoleResourceRlEntity(Long roleId, Long resourceId, ResourceTypeEnum resourceType) {
        this.roleId = roleId;
        this.resourceId = resourceId;
        this.resourceType = resourceType;
    }

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("resource_id")
    private Long resourceId;

    @TableField("resource_type")
    private ResourceTypeEnum resourceType;
}
