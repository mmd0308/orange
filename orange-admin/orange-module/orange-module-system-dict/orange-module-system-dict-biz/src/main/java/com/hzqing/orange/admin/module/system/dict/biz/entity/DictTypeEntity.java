package com.hzqing.orange.admin.module.system.dict.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import com.hzqing.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_dict_type")
public class DictTypeEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 字典名称
     */
    @TableField("name")
    private String name;

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 状态
     */
    @TableField(value = "status")
    private CommonDataStatusEnum status;


    /**
     * 预设标志（0:预置 1:自定义）
     */
    @TableField("preset_flag")
    private DataPresetFlagEnum presetFlag;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
