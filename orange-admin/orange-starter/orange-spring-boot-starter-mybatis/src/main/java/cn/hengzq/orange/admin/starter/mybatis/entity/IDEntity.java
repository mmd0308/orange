package cn.hengzq.orange.admin.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;


/**
 * @author 程序员橙子
 */
public class IDEntity implements Serializable {

    /**
     * 主键
     */
    @TableField("id")
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
