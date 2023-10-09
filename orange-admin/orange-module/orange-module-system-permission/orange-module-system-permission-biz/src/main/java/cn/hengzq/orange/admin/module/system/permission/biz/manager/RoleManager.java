package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;

/**
 *@author 程序员橙子
 */
public interface RoleManager extends BaseManager<RoleEntity> {

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    Page<RoleEntity> page(Integer pageNo, Integer pageSize, RoleListQuery query);

    /**
     * 根据条件查询所有数据
     *
     * @param query
     * @return
     */
    List<RoleEntity> listByParams(RoleListQuery query);

    /**
     * 根据用户id 查询用户所有用的角色
     *
     * @param userId
     * @return
     */
    List<RoleEntity> listByUserId(Long userId);
}
