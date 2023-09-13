package com.hzqing.orange.admin.module.system.dict.biz.manager;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import com.hzqing.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface DictDataManager extends BaseManager<DictDataEntity> {

    /**
     * 根据条件查询所有的数据
     */
    List<DictDataEntity> listByParams(DictDataListQuery query);

    /**
     * 根据字典类型 获取数据
     */
    default List<DictDataEntity> listByType(String dictType) {
        LambdaQueryWrapper<DictDataEntity> queryWrapper = Wrappers.<DictDataEntity>lambdaQuery()
                .eq(DictDataEntity::getDictType, dictType)
                .orderByAsc(DictDataEntity::getSort);
        return getMapper().selectList(queryWrapper);
    }

    /**
     * 分页查询
     */
    Page<DictDataEntity> page(Integer pageNo, Integer pageSize, DictDataListQuery query);

}
