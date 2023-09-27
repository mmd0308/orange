package com.hzqing.orange.admin.module.system.permission.biz.service;


import com.hzqing.orange.admin.module.system.permission.common.vo.MenuTreeVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.MenuVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.MenuTreeQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.MenuUpdateRequest;

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
    Long add(MenuAddRequest request);

    /**
     * 根据ID更新
     */
    Boolean updateById(Long id, MenuUpdateRequest request);

    /**
     * 根据ID删除数据
     */
    Boolean removeById(Long id);

}
