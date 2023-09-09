package com.hzqing.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hzqing.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import com.hzqing.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Repository
public interface DepartmentMapper extends BaseMapper<DepartmentEntity> {

    /**
     * 根据父节点查询数据
     *
     * @param parentId
     * @return
     */
    default List<DepartmentEntity> selectByParentId(Long parentId) {
        return this.selectList(Wrappers.<DepartmentEntity>lambdaQuery().eq(DepartmentEntity::getParentId, parentId));
    }
}
