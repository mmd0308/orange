package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface DepartmentManager extends BaseManager<DepartmentEntity> {

    /**
     * 根据父级ID查询子集数据
     */
    List<DepartmentEntity> listByParentId(Long parentId);

    List<DepartmentEntity> listByParentIds(List<Long> parentIds);

    List<DepartmentEntity> listByParams(DepartmentListQuery queryDTO);


    List<DepartmentEntity> listSubsetByParentId(Long parentId);
}
