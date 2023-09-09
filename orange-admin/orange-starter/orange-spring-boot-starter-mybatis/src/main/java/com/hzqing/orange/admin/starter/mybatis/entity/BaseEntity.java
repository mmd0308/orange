package com.hzqing.orange.admin.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;

/**
 * 基础实体类
 *
 * @author 程序员橙子
 */
public class BaseEntity extends IDEntity {

    /**
     * 创建者账号
     */
    @TableField("created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private Long createdAt;

    /**
     * 更新者账号
     */
    @TableField("updated_by")
    private Long updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private Long updatedAt;

    public void initParams(boolean add) {
        if (add) {
            this.setCreatedBy(GlobalContextHelper.getCurrentUserId());
            this.setCreatedAt(System.currentTimeMillis());
        }
        this.setUpdatedBy(GlobalContextHelper.getCurrentUserId());
        this.setUpdatedAt(System.currentTimeMillis());
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
