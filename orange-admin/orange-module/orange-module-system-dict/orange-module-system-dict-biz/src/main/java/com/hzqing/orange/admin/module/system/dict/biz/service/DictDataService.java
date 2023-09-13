package com.hzqing.orange.admin.module.system.dict.biz.service;


import com.hzqing.orange.admin.module.system.dict.common.vo.DictDataVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictDataPageQuery;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

/**
 * @author 程序员橙子
 */
public interface DictDataService {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    PageVO<DictDataVO> page(DictDataPageQuery query);
}
