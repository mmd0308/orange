package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  cn.hengzq.orange.admin.module.system.permission.biz.dto.UserListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface UserManager extends BaseManager<UserEntity> {

    Page<UserEntity> page(Integer pageNo, Integer pageSize, UserListQuery listQuery);

    List<UserEntity> listByParams(UserListQuery query);

    UserEntity getByUsername(String username);
}
