package com.hzqing.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hzqing.orange.admin.module.system.permission.biz.entity.UserRoleRlEntity;
import com.hzqing.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Repository
public interface UserRoleRlMapper extends BaseMapper<UserRoleRlEntity> {

    /**
     * 批量插入数据
     *
     * @param userRoles
     * @return
     */
    Integer batchInsert(List<UserRoleRlEntity> userRoles);


    /**
     * 根据用户ID查询
     *
     * @param userId
     * @return
     */
    default List<UserRoleRlEntity> selectByUserId(Long userId) {
        return this.selectList(Wrappers.<UserRoleRlEntity>lambdaQuery().eq(UserRoleRlEntity::getUserId, userId));
    }

    /**
     * 根据角色ID查询
     *
     * @param roleId
     * @return
     */
    default List<UserRoleRlEntity> selectByRoleId(Long roleId) {
        return this.selectList(Wrappers.<UserRoleRlEntity>lambdaQuery().eq(UserRoleRlEntity::getRoleId, roleId));
    }


    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    default Boolean removeByUserId(Long userId) {
        this.delete(Wrappers.<UserRoleRlEntity>lambdaQuery().eq(UserRoleRlEntity::getUserId, userId));
        return Boolean.TRUE;
    }

}
