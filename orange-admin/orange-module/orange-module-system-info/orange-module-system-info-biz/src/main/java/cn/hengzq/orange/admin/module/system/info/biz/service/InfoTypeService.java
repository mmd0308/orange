package cn.hengzq.orange.admin.module.system.info.biz.service;

import cn.hengzq.orange.admin.module.system.info.common.vo.InfoTypeVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypeAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypePageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

import java.util.List;

public interface InfoTypeService {
    PageVO<InfoTypeVO> page(InfoTypePageQuery queryVo);

    List<InfoTypeVO> queryAll(InfoTypeAllQuery query);

    Long add(InfoTypeAddOrUpdateRequest request);

    Boolean updateById(Long id, InfoTypeAddOrUpdateRequest request);

    Boolean removeById(Long id);
}
