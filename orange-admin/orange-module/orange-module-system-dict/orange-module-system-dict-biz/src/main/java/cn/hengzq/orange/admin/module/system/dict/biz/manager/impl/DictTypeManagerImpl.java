package cn.hengzq.orange.admin.module.system.dict.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictTypeListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import cn.hengzq.orange.admin.module.system.dict.biz.manager.DictTypeManager;
import cn.hengzq.orange.admin.module.system.dict.biz.mapper.DictTypeMapper;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class DictTypeManagerImpl extends BaseManagerImpl<DictTypeMapper, DictTypeEntity> implements DictTypeManager {

    private final DictTypeMapper mapper;

    @Override
    public DictTypeMapper getMapper() {
        return this.mapper;
    }

    private static LambdaQueryWrapper<DictTypeEntity> getQueryWrapper(DictTypeListQuery query) {
        return Wrappers.<DictTypeEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getName()), DictTypeEntity::getName, query.getName())
                .eq(StrUtil.isNotBlank(query.getDictType()), DictTypeEntity::getDictType, query.getDictType())
                .like(StrUtil.isNotBlank(query.getNameLike()), DictTypeEntity::getName, query.getNameLike())
                .like(StrUtil.isNotBlank(query.getDictTypeLike()), DictTypeEntity::getDictType, query.getDictTypeLike())
                .orderByDesc(BaseEntity::getUpdatedAt);
    }

    @Override
    public Page<DictTypeEntity> page(Integer pageNo, Integer pageSize, DictTypeListQuery query) {
        return mapper.selectPage(new Page<DictTypeEntity>(pageNo, pageSize), getQueryWrapper(query));
    }

    @Override
    public List<DictTypeEntity> listByParams(DictTypeListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }
}
