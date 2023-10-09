package cn.hengzq.orange.admin.starter.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;

/**
 * 包含租户的基础实体类
 *
 * @author 程序员橙子
 */
public class BaseTenantEntity extends BaseEntity {

    /**
     * 租户id
     */
    @TableField("tenant_id")
    private Long tenantId;

    public void initAddParams() {
        initParams(Boolean.TRUE);
    }

    public void initUpdateParams() {
        initParams(Boolean.FALSE);
    }

    @Override
    public void initParams(boolean add) {
        super.initParams(add);
        this.setTenantId(GlobalContextHelper.getTenantId());
    }


    public Long getTenantId() {
        return tenantId == null ? GlobalContextHelper.getTenantId() : tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }
}
