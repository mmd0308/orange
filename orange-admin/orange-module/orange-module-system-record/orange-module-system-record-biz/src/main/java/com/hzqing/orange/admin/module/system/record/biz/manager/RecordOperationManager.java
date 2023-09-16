package com.hzqing.orange.admin.module.system.record.biz.manager;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import com.hzqing.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;


/**
 * @author 程序员橙子
 */
public interface RecordOperationManager extends BaseManager<RecordOperationEntity> {


    /**
     * 清空操作日志
     */
    void clear();

    /**
     * 分页查询
     *
     */
    Page<RecordOperationEntity> page(OperationRecordPageQuery query);
}
