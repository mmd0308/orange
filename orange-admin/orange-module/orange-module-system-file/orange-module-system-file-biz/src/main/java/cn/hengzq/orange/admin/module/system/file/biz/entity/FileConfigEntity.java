package cn.hengzq.orange.admin.module.system.file.biz.entity;

import cn.hengzq.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import cn.hengzq.starter.storage.core.StorageConfig;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件配置管理
 *
 * @author 程序员橙子
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys_file_config", autoResultMap = true)
public class FileConfigEntity extends BaseTenantEntity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("storage")
    private String storage;

    @TableField("name")
    private String name;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private StorageConfig config;

    @TableField("master")
    private Boolean master;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
}
