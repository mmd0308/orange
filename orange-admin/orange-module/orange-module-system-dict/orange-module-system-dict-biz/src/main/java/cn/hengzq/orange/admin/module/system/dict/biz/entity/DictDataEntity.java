package cn.hengzq.orange.admin.module.system.dict.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.hengzq.orange.admin.starter.common.constants.enums.support.CommonDataStatusEnum;
import cn.hengzq.orange.admin.starter.common.constants.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
@TableName(value = "sys_dict_data")
public class DictDataEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("sort")
    private Integer sort;

    @TableField("dict_label")
    private String dictLabel;

    @TableField("dict_value")
    private String dictValue;

    @TableField("dict_type")
    private String dictType;

    @TableField(value = "status")
    private CommonDataStatusEnum status;

    /**
     * 预设标志（0:预置 1:自定义）
     */
    @TableField("preset_flag")
    private DataPresetFlagEnum presetFlag;

    /**
     * 回显样式
     */
    @TableField("show_style")
    private String showStyle;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
