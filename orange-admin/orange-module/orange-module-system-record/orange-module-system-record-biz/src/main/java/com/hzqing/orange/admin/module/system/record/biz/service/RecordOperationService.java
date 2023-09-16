package com.hzqing.orange.admin.module.system.record.biz.service;


import com.hzqing.orange.admin.module.system.record.common.vo.RecordOperationVO;
import com.hzqing.orange.admin.module.system.record.common.vo.query.OperationRecordExportQuery;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface RecordOperationService {


    /**
     * 导出列表查询
     */
    List<RecordOperationVO> queryExportList(OperationRecordExportQuery query);

    /**
     * 添加新的日志记录
     */
    Long add(RecordOperationVO recordOperationVO);
}
