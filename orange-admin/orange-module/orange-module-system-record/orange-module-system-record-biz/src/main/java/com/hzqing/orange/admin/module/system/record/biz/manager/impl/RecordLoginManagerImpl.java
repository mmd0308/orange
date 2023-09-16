package com.hzqing.orange.admin.module.system.record.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import com.hzqing.orange.admin.module.system.record.biz.manager.RecordLoginManager;
import com.hzqing.orange.admin.module.system.record.biz.mapper.RecordLoginMapper;
import com.hzqing.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RecordLoginManagerImpl extends BaseManagerImpl<RecordLoginMapper, RecordLoginEntity>
        implements RecordLoginManager {

    private final RecordLoginMapper mapper;

    @Override
    public RecordLoginMapper getMapper() {
        return this.mapper;
    }


    @Override
    public Page<RecordLoginEntity> page(Integer pageNo, Integer pageSize, RecordLoginListQuery query) {
        LambdaQueryWrapper<RecordLoginEntity> queryWrapper = Wrappers.<RecordLoginEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getAccount()), RecordLoginEntity::getAccount, query.getAccount());

        if (Objects.nonNull(query.getStartLoginTime()) && Objects.nonNull(query.getEndLoginTime())) {
            queryWrapper.between(RecordLoginEntity::getLoginTime, query.getStartLoginTime(), query.getEndLoginTime());
        }

        queryWrapper.orderByDesc(RecordLoginEntity::getLoginTime);
        return mapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);
    }
}
