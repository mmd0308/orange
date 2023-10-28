package cn.hengzq.orange.admin.module.system.info.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoTypeManager;
import cn.hengzq.orange.admin.module.system.info.biz.mapper.InfoTypeMapper;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class InfoTypeManagerImpl extends BaseManagerImpl<InfoTypeMapper, InfoTypeEntity>
        implements InfoTypeManager {

    private final InfoTypeMapper mapper;

    @Override
    public InfoTypeMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<InfoTypeEntity> page(Integer pageNo, Integer pageSize, InfoTypeListQuery query) {
        LambdaQueryWrapper<InfoTypeEntity> queryWrapper = getQueryWrapper(query);
        return mapper.selectPage(new Page<InfoTypeEntity>(pageNo, pageSize), queryWrapper);
    }

    private static LambdaQueryWrapper<InfoTypeEntity> getQueryWrapper(InfoTypeListQuery query) {
        return Wrappers.<InfoTypeEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getName()), InfoTypeEntity::getName, query.getName())
                .eq(StrUtil.isNotBlank(query.getCode()), InfoTypeEntity::getCode, query.getCode())
                .eq(Objects.nonNull(query.getParentId()), InfoTypeEntity::getParentId, query.getParentId())
                .like(StrUtil.isNotBlank(query.getNameLike()), InfoTypeEntity::getName, query.getNameLike())
                .like(StrUtil.isNotBlank(query.getCodeLike()), InfoTypeEntity::getCode, query.getCodeLike())
                .in(CollUtil.isNotEmpty(query.getIds()), InfoTypeEntity::getId, query.getIds())
                .orderByAsc(InfoTypeEntity::getSort);
    }

    @Override
    public List<InfoTypeEntity> listByParams(InfoTypeListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }
}
