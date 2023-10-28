package cn.hengzq.orange.admin.module.system.info.biz.service.impl;

import cn.hengzq.orange.admin.module.system.info.biz.convert.InfoConverter;
import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoEntity;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoManager;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoTypeManager;
import cn.hengzq.orange.admin.module.system.info.biz.service.InfoService;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoSimpleVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoPageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.GlobalErrorCodeConstant;
import cn.hengzq.orange.admin.starter.common.util.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {

    private final InfoManager manager;

    private final InfoTypeManager typeManager;

    @Override
    public PageVO<InfoSimpleVO> page(InfoPageQuery queryVo) {
        InfoListQuery listQuery = InfoConverter.INSTANCE.toListQuery(queryVo);
        Page<InfoEntity> page = manager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        PageVO<InfoSimpleVO> result = InfoConverter.INSTANCE.toPage(page);
        List<InfoSimpleVO> records = result.getRecords();
        wrapTypeName(records);
        return result;
    }


    @Override
    public List<InfoSimpleVO> queryAll(InfoAllQuery query) {
        List<InfoEntity> entityList = manager.listByParams(InfoConverter.INSTANCE.toListQuery(query));
        List<InfoSimpleVO> result = InfoConverter.INSTANCE.toSimpleListVo(entityList);
        wrapTypeName(result);
        return result;
    }

    private void wrapTypeName(List<InfoSimpleVO> records) {
        if (CollUtil.isEmpty(records)) {
            return;
        }
        List<Long> typeIds = records.stream().map(InfoSimpleVO::getTypeId).toList();
        Map<Long, String> typeNameMap = getTypeNameMap(typeIds);
        records.stream().filter(item -> Objects.nonNull(item) && Objects.nonNull(item.getTypeId()))
                .forEach(i -> {
                    if (typeNameMap.containsKey(i.getTypeId())) {
                        i.setTypeName(typeNameMap.get(i.getTypeId()));
                    }
                });
    }

    private Map<Long, String> getTypeNameMap(List<Long> typeIds) {
        if (CollUtil.isEmpty(typeIds)) {
            return Map.of();
        }
        List<InfoTypeEntity> entityList = typeManager.listByParams(InfoTypeListQuery.builder().ids(typeIds).build());
        if (CollUtil.isEmpty(entityList)) {
            return Map.of();
        }
        return entityList.stream().collect(Collectors.toMap(InfoTypeEntity::getId, InfoTypeEntity::getName));
    }

    @Override
    public Long add(InfoAddOrUpdateRequest request) {
        InfoEntity entity = InfoConverter.INSTANCE.toEntity(request);
        return manager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, InfoAddOrUpdateRequest request) {
        InfoEntity entity = manager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstant.GLOBAL_DATA_NOT_EXIST);
        entity = InfoConverter.INSTANCE.updateConvert(entity, request);
        return manager.updateById(entity);
    }

    @Override
    public Boolean removeById(Long id) {
        InfoEntity entity = manager.getById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        return manager.removeById(id);
    }
}
