package cn.hengzq.orange.admin.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;

import java.time.LocalDateTime;

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
    private LocalDateTime createdAt;

    /**
     * 更新者账号
     */
    @TableField("updated_by")
    private Long updatedBy;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    public void initParams(boolean add) {
        if (add) {
            this.setCreatedBy(GlobalContextHelper.getCurrentUserId());
            this.setCreatedAt(LocalDateTime.now());
        }
        this.setUpdatedBy(GlobalContextHelper.getCurrentUserId());
        this.setUpdatedAt(LocalDateTime.now());
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
