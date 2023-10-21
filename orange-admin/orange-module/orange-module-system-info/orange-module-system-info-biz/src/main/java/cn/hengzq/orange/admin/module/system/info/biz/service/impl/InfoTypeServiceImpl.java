package cn.hengzq.orange.admin.module.system.info.biz.service.impl;

import cn.hengzq.orange.admin.module.system.info.biz.convert.InfoTypeConverter;
import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoTypeManager;
import cn.hengzq.orange.admin.module.system.info.biz.service.InfoTypeService;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoTypeVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypeAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypePageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.util.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InfoTypeServiceImpl implements InfoTypeService {

    private InfoTypeManager manager;

    @Override
    public PageVO<InfoTypeVO> page(InfoTypePageQuery queryVo) {
        InfoTypeListQuery listQuery = InfoTypeConverter.INSTANCE.toListQuery(queryVo);
        Page<InfoTypeEntity> page = manager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        return InfoTypeConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<InfoTypeVO> queryAll(InfoTypeAllQuery query) {
        List<InfoTypeEntity> entityList = manager.listByParams(InfoTypeConverter.INSTANCE.toListQuery(query));
        return InfoTypeConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public Long add(InfoTypeAddOrUpdateRequest request) {
        InfoTypeEntity entity = InfoTypeConverter.INSTANCE.toEntity(request);
        return manager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, InfoTypeAddOrUpdateRequest request) {
        InfoTypeEntity entity = manager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
//        if (StrUtil.isNotBlank(request.getPermission()) && !request.getPermission().equals(entity.getPermission())) {
//            List<ButtonEntity> entityList = buttonManager.listByParams(ButtonListQuery.builder().permission(request.getPermission()).build());
//            Assert.isEmpty(entityList, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
//        }
        entity = InfoTypeConverter.INSTANCE.updateConvert(entity, request);
        return manager.updateById(entity);
    }
}
