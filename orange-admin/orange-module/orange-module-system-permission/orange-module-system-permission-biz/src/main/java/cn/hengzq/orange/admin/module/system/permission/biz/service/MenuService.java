package cn.hengzq.orange.admin.module.system.permission.biz.service;


import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuTreeVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.MenuAddOrUpdateRequest;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface MenuService {

    List<MenuTreeVO> queryTree(MenuTreeQuery query);

    /**
     * 根据条件查询菜单数据
     */
    List<MenuVO> queryByParams(MenuAllQuery query);

    /**
     * 根据角色ID 查询菜单
     */
    List<MenuVO> queryByRoleIds(List<Long> roleIds);

    /**
     * 新增菜单
     */
    Long add(MenuAddOrUpdateRequest request);

    /**
     * 根据ID更新
     */
    Boolean updateById(Long id, MenuAddOrUpdateRequest request);

    /**
     * 根据ID删除数据
     */
    Boolean removeById(Long id);

}
