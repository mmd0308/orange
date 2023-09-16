package com.hzqing.orange.admin.module.system.record.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;


/**
 * @author 程序员橙子
 */
public interface RecordLoginManager extends BaseManager<RecordLoginEntity> {

    Page<RecordLoginEntity> page(Integer pageNo, Integer pageSize, RecordLoginListQuery query);
}
