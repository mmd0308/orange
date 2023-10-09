package cn.hengzq.orange.admin.module.system.permission.biz.manager;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.MenuEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface MenuManager extends BaseManager<MenuEntity> {
    List<MenuEntity> listByParams(MenuListQuery query);


//    List<MenuTree> queryTree(MenuTreeQuery query);
//
//    List<MenuButtonTree> queryMenuTreeAndButton();

}
