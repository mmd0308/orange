package com.hzqing.orange.admin.module.system.permission.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseTenantEntity;
import lombok.Data;


/**
 *@author 程序员橙子
 */
@Data
@TableName(value = "sys_department")
public class DepartmentEntity extends BaseTenantEntity {

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

    private String ancestors;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sort;
}
