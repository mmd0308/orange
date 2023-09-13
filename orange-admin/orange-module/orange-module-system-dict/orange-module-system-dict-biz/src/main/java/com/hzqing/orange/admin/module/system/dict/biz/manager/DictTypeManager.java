package com.hzqing.orange.admin.module.system.dict.biz.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictTypeListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;

/**
 * @author 程序员橙子
 */
public interface DictTypeManager extends BaseManager<DictTypeEntity> {


    /**
     * 分页查询
     */
    Page<DictTypeEntity> page(Integer pageNo, Integer pageSize, DictTypeListQuery query);

    /**
     * 根据条件查询所有的数据
     */
    List<DictTypeEntity> listByParams(DictTypeListQuery query);
}
