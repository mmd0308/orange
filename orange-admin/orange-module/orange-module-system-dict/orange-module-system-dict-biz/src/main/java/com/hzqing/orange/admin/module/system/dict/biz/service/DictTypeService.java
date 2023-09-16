package com.hzqing.orange.admin.module.system.dict.biz.service;

import com.hzqing.orange.admin.module.system.dict.common.vo.DictTypeVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictTypeAllQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
import com.hzqing.orange.admin.starter.common.vo.PageVO;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface DictTypeService {


    PageVO<DictTypeVO> page(DictTypePageQuery queryVo);

    Long add(DictTypeAddRequest request);

    Boolean updateById(Long id, DictTypeUpdateRequest request);

    Boolean removeById(Long id);

    List<DictTypeVO> queryAll(DictTypeAllQuery queryVo);
}
