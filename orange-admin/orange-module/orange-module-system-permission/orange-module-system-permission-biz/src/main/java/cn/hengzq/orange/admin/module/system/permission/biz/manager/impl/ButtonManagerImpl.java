package cn.hengzq.orange.admin.module.system.permission.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.ButtonManager;
import cn.hengzq.orange.admin.module.system.permission.biz.mapper.ButtonMapper;
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
public class ButtonManagerImpl extends BaseManagerImpl<ButtonMapper, ButtonEntity> implements ButtonManager {

    private final ButtonMapper mapper;

    @Override
    public ButtonMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<ButtonEntity> page(Integer pageNo, Integer pageSize, ButtonListQuery query) {
        LambdaQueryWrapper<ButtonEntity> queryWrapper = getQueryWrapper(query);
        return mapper.selectPage(new Page<ButtonEntity>(pageNo, pageSize), queryWrapper);
    }

    private static LambdaQueryWrapper<ButtonEntity> getQueryWrapper(ButtonListQuery query) {
        return Wrappers.<ButtonEntity>lambdaQuery()
                .eq(Objects.nonNull(query.getMenuId()), ButtonEntity::getMenuId, query.getMenuId())
                .eq(Objects.nonNull(query.getPermission()), ButtonEntity::getPermission, query.getPermission())
                .in(CollUtil.isNotEmpty(query.getMenuIds()), ButtonEntity::getMenuId, query.getMenuIds())
                .like(StrUtil.isNotBlank(query.getPermissionLike()), ButtonEntity::getPermission, query.getPermissionLike())
                .orderByAsc(ButtonEntity::getSort);
    }

    @Override
    public List<ButtonEntity> listByParams(ButtonListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }
}
