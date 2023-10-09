package cn.hengzq.orange.admin.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;


/**
 * @author 程序员橙子
 */
public class IDEntity extends AbstractEntity {

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
