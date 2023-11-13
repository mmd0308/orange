package cn.hengzq.orange.admin.module.system.file.biz.entity;

import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件实体
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_file")
public class FileEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("config_id")
    private Long configId;

    @TableField("original_name")
    private String originalName;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

    @TableField("type")
    private String type;

    @TableField("size")
    private Long size;

    @TableField("url")
    private String url;
}
