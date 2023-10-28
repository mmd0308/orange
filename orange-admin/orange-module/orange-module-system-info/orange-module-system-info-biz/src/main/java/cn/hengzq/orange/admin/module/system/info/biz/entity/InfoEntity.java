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
 * 信息
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_info")
public class InfoEntity extends BaseTenantEntity {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 消息类型ID
     */
    @TableField("type_id")
    private Long typeId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 排序
     */
    private Integer sort;
}
