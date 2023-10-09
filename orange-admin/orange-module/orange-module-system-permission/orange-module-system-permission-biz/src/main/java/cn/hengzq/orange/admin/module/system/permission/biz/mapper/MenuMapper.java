package cn.hengzq.orange.admin.module.system.permission.biz.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.MenuEntity;
import cn.hengzq.orange.admin.starter.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author 程序员橙子
 */
@Repository
public interface MenuMapper extends BaseMapper<MenuEntity> {

    /**
     * 根据父节点id查询
     */
    default List<MenuEntity> selectByParentId(Long parentId) {
        return this.selectList(Wrappers.<MenuEntity>lambdaQuery().eq(MenuEntity::getParentId, parentId));
    }
}
