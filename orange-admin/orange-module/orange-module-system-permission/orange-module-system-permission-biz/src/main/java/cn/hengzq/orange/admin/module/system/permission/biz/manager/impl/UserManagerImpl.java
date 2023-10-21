package cn.hengzq.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.UserListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.UserManager;
import cn.hengzq.orange.admin.module.system.permission.biz.mapper.UserMapper;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;
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
public class UserManagerImpl extends BaseManagerImpl<UserMapper, UserEntity> implements UserManager {

    private final UserMapper mapper;

    @Override
    public UserMapper getMapper() {
        return this.mapper;
    }

    private static LambdaQueryWrapper<UserEntity> getQueryWrapper(UserListQuery query) {
        return Wrappers.<UserEntity>lambdaQuery()
                .eq(Objects.nonNull(query.getDepartmentId()), UserEntity::getDepartmentId, query.getDepartmentId())
                .in(CollUtil.isNotEmpty(query.getDepartmentIds()), UserEntity::getDepartmentId, query.getDepartmentIds())
                .eq(StrUtil.isNotBlank(query.getName()), UserEntity::getName, query.getName())
                .like(StrUtil.isNotBlank(query.getNameLike()), UserEntity::getName, query.getNameLike())
                .eq(StrUtil.isNotBlank(query.getUsername()), UserEntity::getUsername, query.getUsername())
                .like(StrUtil.isNotBlank(query.getUsernameLike()), UserEntity::getUsername, query.getUsernameLike())
                .eq(StrUtil.isNotBlank(query.getPhone()), UserEntity::getPhone, query.getPhone())
                .like(StrUtil.isNotBlank(query.getPhoneLike()), UserEntity::getPhone, query.getPhoneLike())
                .eq(StrUtil.isNotBlank(query.getEmail()), UserEntity::getEmail, query.getEmail())
                .like(StrUtil.isNotBlank(query.getEmailLike()), UserEntity::getEmail, query.getEmailLike())
                .orderByDesc(BaseEntity::getUpdatedAt);
    }

    @Override
    public Page<UserEntity> page(Integer pageNo, Integer pageSize, UserListQuery listQuery) {
        return mapper.selectPage(new Page<UserEntity>(pageNo, pageSize), getQueryWrapper(listQuery));
    }

    @Override
    public List<UserEntity> listByParams(UserListQuery listQuery) {
        return mapper.selectList(getQueryWrapper(listQuery));
    }

    @Override
    public UserEntity getByUsername(String username) {
        return mapper.selectOneByUsername(username);
    }
    
}
