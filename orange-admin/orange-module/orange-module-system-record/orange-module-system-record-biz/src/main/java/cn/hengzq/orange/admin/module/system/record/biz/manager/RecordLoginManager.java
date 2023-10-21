package cn.hengzq.orange.admin.module.system.record.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;


/**
 * @author 程序员橙子
 */
public interface RecordLoginManager extends BaseManager<RecordLoginEntity> {

    Page<RecordLoginEntity> page(Integer pageNo, Integer pageSize, RecordLoginListQuery query);

    void removeAll();


}
