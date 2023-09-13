package com.hzqing.orange.admin.module.system.dict.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import com.hzqing.orange.admin.module.system.dict.biz.manager.DictDataManager;
import com.hzqing.orange.admin.module.system.dict.biz.mapper.DictDataMapper;
import com.hzqing.orange.admin.starter.mybatis.entity.BaseEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class DictDataManagerImpl extends BaseManagerImpl<DictDataMapper, DictDataEntity> implements DictDataManager {
    private final DictDataMapper mapper;

    @Override
    public DictDataMapper getMapper() {
        return this.mapper;
    }

    @Override
    public List<DictDataEntity> listByParams(DictDataListQuery listQuery) {
        return mapper.selectList(getQueryWrapper(listQuery));
    }

    @Override
    public Page<DictDataEntity> page(Integer pageNo, Integer pageSize, DictDataListQuery listQuery) {
        return mapper.selectPage(new Page<>(pageNo, pageSize), getQueryWrapper(listQuery));
    }

    private static LambdaQueryWrapper<DictDataEntity> getQueryWrapper(DictDataListQuery query) {
        return Wrappers.<DictDataEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getDictType()), DictDataEntity::getDictType, query.getDictType())
                .eq(Objects.nonNull(query.getPresetFlag()), DictDataEntity::getPresetFlag, query.getPresetFlag())
                .eq(StrUtil.isNotBlank(query.getDictLabel()), DictDataEntity::getDictLabel, query.getDictLabel())
                .like(StrUtil.isNotBlank(query.getDictLabelLike()), DictDataEntity::getDictLabel, query.getDictLabelLike())
                .orderByDesc(BaseEntity::getUpdatedAt);
    }

}
