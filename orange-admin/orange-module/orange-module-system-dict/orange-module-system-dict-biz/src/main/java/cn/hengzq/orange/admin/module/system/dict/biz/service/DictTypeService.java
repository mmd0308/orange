package cn.hengzq.orange.admin.module.system.dict.biz.service;

import cn.hengzq.orange.admin.module.system.dict.common.vo.DictTypeVO;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypeAllQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

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
