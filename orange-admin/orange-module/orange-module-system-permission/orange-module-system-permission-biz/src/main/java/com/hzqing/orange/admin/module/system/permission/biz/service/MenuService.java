package com.hzqing.orange.admin.module.system.permission.biz.service;


import com.hzqing.orange.admin.module.system.permission.common.vo.Menu;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuButtonTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTree;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;

import java.util.List;


/**
 *@author 程序员橙子
 */
public interface MenuService {

    List<MenuTree> queryTree(MenuTreeQuery query);

    List<MenuButtonTree> queryAllMenuAndButtonTree();

    /**
     * 根据条件查询菜单数据
     *
     * @param query
     * @return
     */
    List<Menu> queryByParams(MenuAllQuery query);

    /**
     * 根据角色ID 查询菜单
     *
     * @param roleIds
     * @return
     */
    List<Menu> queryByRoleIds(List<Long> roleIds);

    /**
     * 新增菜单
     *
     * @param request 请求参数
     * @return 返回新增数据ID
     */
    Long add(MenuAddRequest request);

    /**
     * 根据ID更新
     *
     * @param id
     * @param request
     * @return
     */
    Boolean updateById(Long id, MenuUpdateRequest request);

    /**
     * 根据ID删除数据
     *
     * @param id
     * @return
     */
    Boolean removeById(Long id);
}
