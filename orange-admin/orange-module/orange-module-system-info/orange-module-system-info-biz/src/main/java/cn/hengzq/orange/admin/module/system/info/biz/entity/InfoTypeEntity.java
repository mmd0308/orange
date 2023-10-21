package cn.hengzq.orange.admin.module.system.info.biz.entity;

import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 类型
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_info_type")
public class InfoTypeEntity extends BaseTenantEntity {

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

    /**
     * 类型名称
     */
    @TableField("name")
    private String name;

    /**
     * 类型编码
     */
    @TableField("code")
    private String code;

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
