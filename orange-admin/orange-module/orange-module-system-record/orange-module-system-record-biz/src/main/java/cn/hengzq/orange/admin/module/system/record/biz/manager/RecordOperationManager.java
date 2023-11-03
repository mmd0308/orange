package cn.hengzq.orange.admin.module.system.record.biz.manager;


import cn.hengzq.orange.admin.module.system.record.biz.dto.RecordOperationListQuery;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * @author 程序员橙子
 */
public interface RecordOperationManager extends BaseManager<RecordOperationEntity> {


    /**
     * 删除所有的数据
     */
    void removeAll();

    /**
     * 分页查询
     */
    Page<RecordOperationEntity> page(Integer pageNo, Integer pageSize, RecordOperationListQuery query);
}
