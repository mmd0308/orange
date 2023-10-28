package cn.hengzq.orange.admin.module.system.info.biz.service;

import cn.hengzq.orange.admin.module.system.info.common.vo.InfoSimpleVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoPageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoAddOrUpdateRequest;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

import java.util.List;

public interface InfoService {
    PageVO<InfoSimpleVO> page(InfoPageQuery queryVo);

    List<InfoSimpleVO> queryAll(InfoAllQuery query);

    Long add(InfoAddOrUpdateRequest request);

    Boolean updateById(Long id, InfoAddOrUpdateRequest request);

    Boolean removeById(Long id);
}
