package cn.hengzq.orange.admin.module.system.record.biz.service;


import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordExportQuery;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

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

    PageVO<RecordOperationVO> page(OperationRecordPageQuery queryVo);

    RecordOperationVO getById(Long id);
}
