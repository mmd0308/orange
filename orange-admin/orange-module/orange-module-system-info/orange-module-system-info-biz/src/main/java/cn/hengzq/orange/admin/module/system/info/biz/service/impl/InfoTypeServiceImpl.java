package cn.hengzq.orange.admin.module.system.info.biz.service.impl;

import cn.hengzq.orange.admin.module.system.info.biz.convert.InfoTypeConverter;
import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoTypeManager;
import cn.hengzq.orange.admin.module.system.info.biz.service.InfoTypeService;
import cn.hengzq.orange.admin.module.system.info.common.exception.SystemInfoErrorCode;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoTypeVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypeAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypePageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.enums.support.DataPresetFlagEnum;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.util.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class InfoTypeServiceImpl implements InfoTypeService {

    private final InfoTypeManager manager;

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
        if (StrUtil.isNotBlank(request.getCode())) {
            List<InfoTypeEntity> entityList = manager.listByParams(InfoTypeListQuery.builder().code(request.getCode()).build());
            Assert.isEmpty(entityList, SystemInfoErrorCode.TYPE_CODE_CANNOT_REPEAT);
        }
        return manager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, InfoTypeAddOrUpdateRequest request) {
        InfoTypeEntity entity = manager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(request.getCode()) && !request.getCode().equals(entity.getCode())) {
            List<InfoTypeEntity> entityList = manager.listByParams(InfoTypeListQuery.builder().code(request.getCode()).build());
            Assert.isEmpty(entityList, SystemInfoErrorCode.TYPE_CODE_CANNOT_REPEAT);
        }
        entity = InfoTypeConverter.INSTANCE.updateConvert(entity, request);
        return manager.updateById(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        InfoTypeEntity entity = manager.getById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        // 预置数据 不允许删除
        if (DataPresetFlagEnum.PRESET.equals(entity.getPresetFlag())) {
            throw new ServiceException(GlobalErrorCodeConstant.GLOBAL_DATA_PRESET_CANNOT_DELETE);
        }
        // 存在子集类型 不允许删除
        List<InfoTypeEntity> entityList = manager.listByParams(InfoTypeListQuery.builder().parentId(id).build());
        if (CollUtil.isNotEmpty(entityList)) {
            throw new ServiceException(SystemInfoErrorCode.TYPE_DELETE_ERROR_EXIST_SUBSET);
        }
        return manager.removeById(id);
    }
}
