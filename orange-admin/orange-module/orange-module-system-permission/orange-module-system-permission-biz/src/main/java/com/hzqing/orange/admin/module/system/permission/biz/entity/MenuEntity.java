package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理表
 *
 *@author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_menu")
public class MenuEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 父级别ID
     */
    @TableField("parent_id")
    private Long parentId;

    @TableField("root_id")
    private Long rootId;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限编码
     */
    @TableField("permission")
    private String permission;

    /**
     * 预设标志（0:预置 1:自定义）
     */
    @TableField("preset_flag")
    private DataPresetFlagEnum presetFlag;

    /**
     * 菜单路径
     */
    @TableField("path")
    private String path;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    @TableField("hidden")
    private Boolean hidden;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
