package cn.hengzq.orange.admin.starter.mybatis.manager.impl;


import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import cn.hengzq.orange.admin.starter.mybatis.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
public abstract class BaseManagerImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseManager<T> {


    /**
     * 获取当前Mapper
     *
     * @return 返回当前Mapper
     */
    @Override
    public abstract M getMapper();

    @Override
    public T getById(Long id) {
        if (Objects.isNull(id)) {
            log.warn("id is null.");
            return null;
        }
        return getMapper().selectById(id);
    }


}
