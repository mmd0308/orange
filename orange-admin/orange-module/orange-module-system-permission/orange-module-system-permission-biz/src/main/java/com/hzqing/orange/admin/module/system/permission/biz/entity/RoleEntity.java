package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import com.hzqing.orange.admin.starter.mybatis.handlers.EnumCodeTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_role")
public class RoleEntity extends BaseTenantEntity {

    /**
     * 角色id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色权限字符串
     */
    private String permission;

    /**
     * 顺序
     */
    private Integer sort;

    /**
     * 状态
     */
    @TableField(value = "status", typeHandler = EnumCodeTypeHandler.class)
    private CommonDataStatusEnum status;

    @TableField(value = "preset_flag", typeHandler = EnumCodeTypeHandler.class)
    private DataPresetFlagEnum presetFlag;

    /**
     * 备注
     */
    private String remark;

}
