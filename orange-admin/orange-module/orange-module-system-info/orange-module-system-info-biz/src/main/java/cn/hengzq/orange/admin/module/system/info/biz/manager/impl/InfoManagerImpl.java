package cn.hengzq.orange.admin.module.system.info.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoEntity;
import cn.hengzq.orange.admin.module.system.info.biz.manager.InfoManager;
import cn.hengzq.orange.admin.module.system.info.biz.mapper.InfoMapper;
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
public class InfoManagerImpl extends BaseManagerImpl<InfoMapper, InfoEntity> implements InfoManager {

    private final InfoMapper mapper;

    @Override
    public InfoMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<InfoEntity> page(Integer pageNo, Integer pageSize, InfoListQuery query) {
        LambdaQueryWrapper<InfoEntity> queryWrapper = getQueryWrapper(query);
        return mapper.selectPage(new Page<InfoEntity>(pageNo, pageSize), queryWrapper);
    }

    private static LambdaQueryWrapper<InfoEntity> getQueryWrapper(InfoListQuery query) {
        return Wrappers.<InfoEntity>lambdaQuery()
                // 分页和查询所有的数据 不查询content
                .select(InfoEntity.class, i -> !i.getProperty().equals("content"))
                .eq(Objects.nonNull(query.getTypeId()), InfoEntity::getTypeId, query.getTypeId())
                .like(StrUtil.isNotBlank(query.getTitleLike()), InfoEntity::getTitle, query.getTitleLike())
                .in(CollUtil.isNotEmpty(query.getIds()), InfoEntity::getId, query.getIds())
                .in(CollUtil.isNotEmpty(query.getTypeIds()), InfoEntity::getTypeId, query.getTypeIds())
                .orderByAsc(InfoEntity::getSort);
    }

    @Override
    public List<InfoEntity> listByParams(InfoListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }
}
