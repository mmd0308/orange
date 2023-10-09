package cn.hengzq.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.RoleManager;
import cn.hengzq.orange.admin.module.system.permission.biz.mapper.RoleMapper;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
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
public class RoleManagerImpl extends BaseManagerImpl<RoleMapper, RoleEntity> implements RoleManager {

    private final RoleMapper mapper;

    @Override
    public RoleMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<RoleEntity> page(Integer pageNo, Integer pageSize, RoleListQuery query) {
        return mapper.selectPage(new Page<>(pageNo, pageSize), getQueryWrapper(query));
    }

    @Override
    public List<RoleEntity> listByParams(RoleListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }

    @Override
    public List<RoleEntity> listByUserId(Long userId) {
        return mapper.selectListByUserId(userId);
    }

    private static LambdaQueryWrapper<RoleEntity> getQueryWrapper(RoleListQuery query) {
        return Wrappers.<RoleEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getName()), RoleEntity::getName, query.getName())
                .eq(StrUtil.isNotBlank(query.getPermission()), RoleEntity::getPermission, query.getPermission())
                .eq(Objects.nonNull(query.getStatus()), RoleEntity::getStatus, query.getStatus())
                .like(StrUtil.isNotBlank(query.getNameLike()), RoleEntity::getName, query.getNameLike())
                .like(StrUtil.isNotBlank(query.getPermissionLike()), RoleEntity::getPermission, query.getPermissionLike())
                .orderByDesc(RoleEntity::getSort);
    }

}
