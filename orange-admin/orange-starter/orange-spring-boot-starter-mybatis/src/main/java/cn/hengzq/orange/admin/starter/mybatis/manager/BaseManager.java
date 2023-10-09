package cn.hengzq.orange.admin.starter.mybatis.manager;


import cn.hutool.core.collection.CollUtil;
import cn.hengzq.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.validator.Assert;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;
import cn.hengzq.orange.admin.starter.mybatis.mapper.BaseMapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
public interface BaseManager<T extends BaseEntity> {

    /**
     * Mapper
     *
     * @return
     */
    BaseMapper<T> getMapper();


    /**
     * 新建
     *
     * @param entity
     * @return
     */
    default Long add(T entity) {
        if (Objects.isNull(entity)) {
            throw new ServiceException(GlobalErrorCodeConstants.GLOBAL_REQUEST_MISSING_PARAMETER);
        }
        entity.initParams(true);
        getMapper().insert(entity);
        return entity.getId();
    }

    /**
     * 根据ID更新
     *
     * @param id
     * @param entity
     * @return
     */
    default Boolean updateById(Long id, T entity) {
        entity.initParams(false);
        int i = getMapper().updateById(entity);
        return i >= 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 根据ID更新数据
     *
     * @param entity
     * @return
     */
    default Boolean updateById(T entity) {
        Assert.nonNull(entity, GlobalErrorCodeConstants.GLOBAL_PARAMETER_IS_NULL);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstants.GLOBAL_PARAMETER_ID_IS_NULL);
        entity.initParams(false);
        int i = getMapper().updateById(entity);
        return i >= 1 ? Boolean.TRUE : Boolean.FALSE;
    }


    /**
     * 根据多个ID查询结果
     *
     * @param ids
     * @return
     */
    default List<T> listByIds(Collection<? extends Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            return List.of();
        }
        return getMapper().selectBatchIds(ids);
    }


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据ID删除
     *
     * @param id 主键ID
     * @return
     */
    default Boolean removeById(Long id) {
        if (Objects.isNull(id)) {
            throw new ServiceException(GlobalErrorCodeConstants.GLOBAL_PARAMETER_ID_IS_NULL);
        }
        int i = getMapper().deleteById(id);
        return i >= 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 根据多个ID删除
     *
     * @param ids ID集合
     * @return 返回是否删除成功
     */
    default Boolean removeByIds(Collection<Serializable> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw new ServiceException(GlobalErrorCodeConstants.GLOBAL_REQUEST_MISSING_PARAMETER);
        }
        int i = getMapper().deleteBatchIds(ids);
        return i >= 1 ? Boolean.TRUE : Boolean.FALSE;
    }
}
