package cn.hengzq.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import cn.hengzq.orange.admin.module.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Repository
public interface RoleResourceRlMapper extends BaseMapper<RoleResourceRlEntity> {

    /**
     * 批量插入数据
     *
     * @param entityList 新增数据
     */
    void batchInsert(List<RoleResourceRlEntity> entityList);

    /**
     * 根据角色删除所有的关系
     *
     * @param roleId 角色ID
     */
    default void removeByRoleId(Long roleId) {
        this.delete(Wrappers.<RoleResourceRlEntity>lambdaQuery().eq(RoleResourceRlEntity::getRoleId, roleId));
    }

    /**
     * 根据角色ID查询资源
     *
     * @param roleId 角色ID
     * @return
     */
    default List<RoleResourceRlEntity> selectByRoleId(Long roleId) {
        return this.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery().eq(RoleResourceRlEntity::getRoleId, roleId));
    }

    /**
     * 根据多个角色ID批量查询
     *
     * @param roleIds 角色IDs
     * @return
     */
    default List<RoleResourceRlEntity> selectByRoleIds(List<Long> roleIds) {
        return this.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery().in(RoleResourceRlEntity::getRoleId, roleIds));
    }

    /**
     * 根据资源类型和角色id查询
     *
     * @param resourceType 资源类型
     * @param roleIds      角色ID
     * @return
     */
    default List<RoleResourceRlEntity> selectByTypeAndRoleIds(ResourceTypeEnum resourceType, List<Long> roleIds) {
        return this.selectList(Wrappers.<RoleResourceRlEntity>lambdaQuery()
                .in(RoleResourceRlEntity::getRoleId, roleIds)
                .eq(RoleResourceRlEntity::getResourceType, resourceType));
    }
}
