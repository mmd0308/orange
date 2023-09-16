package com.hzqing.orange.admin.module.system.record.biz.service;


import com.hzqing.orange.admin.module.system.record.common.vo.RecordLoginVO;
import com.hzqing.orange.admin.module.system.record.common.vo.query.RecordLoginPageQuery;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

/**
 * @author 程序员橙子
 */
public interface RecordLoginService {

    /**
     * 添加新的日志记录
     */
    Long add(RecordLoginVO RecordLoginVO);

    /**
     * 分页查询
     */
    PageVO<RecordLoginVO> page(RecordLoginPageQuery query);

}
