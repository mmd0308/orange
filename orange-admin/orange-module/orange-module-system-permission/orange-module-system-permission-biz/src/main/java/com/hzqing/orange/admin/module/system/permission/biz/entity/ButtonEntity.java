package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;

/**
 * 按钮管理表
 *
 * @author 程序员橙子
 */
@Data
@TableName(value = "sys_button")
public class ButtonEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 菜单ID
     */
    @TableField("menu_id")
    private Long menuId;

    @TableField("root_id")
    private Long rootId;

    /**
     * 按钮名称
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
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
