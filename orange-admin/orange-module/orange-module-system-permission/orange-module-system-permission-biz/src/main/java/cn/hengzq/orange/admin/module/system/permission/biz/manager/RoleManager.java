package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface RoleManager extends BaseManager<RoleEntity> {

    /**
     * 分页查询
     */
    Page<RoleEntity> page(Integer pageNo, Integer pageSize, RoleListQuery query);

    /**
     * 根据条件查询所有数据
     */
    List<RoleEntity> listByParams(RoleListQuery query);

    /**
     * 根据用户id 查询用户所有用的角色
     */
    List<RoleEntity> listByUserId(Long userId);
}
