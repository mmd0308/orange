package com.hzqing.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.UserEntity;
import com.hzqing.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 *@author 程序员橙子
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    default UserEntity selectOneByUsername(String username) {
        return this.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getUsername, username));
    }

    default UserEntity selectOneByPhone(String phone) {
        return this.selectOne(Wrappers.<UserEntity>lambdaQuery().eq(UserEntity::getPhone, phone));
    }

}
