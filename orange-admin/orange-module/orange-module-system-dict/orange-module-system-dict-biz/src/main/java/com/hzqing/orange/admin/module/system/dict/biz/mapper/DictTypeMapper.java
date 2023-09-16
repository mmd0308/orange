package com.hzqing.orange.admin.module.system.dict.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import com.hzqing.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Repository
public interface DictTypeMapper extends BaseMapper<DictTypeEntity> {


    default List<DictTypeEntity> selectListByType(String dictType) {
        return this.selectList(Wrappers.<DictTypeEntity>lambdaQuery().eq(DictTypeEntity::getDictType, dictType));
    }
}