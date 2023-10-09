package cn.hengzq.orange.admin.starter.mybatis.mapper;


import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;

/**
 * 基础Mapper
 *
 * @author 程序员橙子
 */
public interface BaseMapper<T extends BaseEntity> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {


    /**
     * 插入一条数据
     * 初始化 createdBy,createdAt,updateBy,updateAt
     *
     * @param entity 保存对象
     * @return 返回保存数据ID
     */
    default Long insertOne(T entity) {
        entity.initParams(Boolean.TRUE);
        this.insert(entity);
        return entity.getId();
    }
}
