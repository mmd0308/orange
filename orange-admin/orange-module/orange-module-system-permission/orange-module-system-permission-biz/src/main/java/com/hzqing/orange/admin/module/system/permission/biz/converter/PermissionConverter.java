package com.hzqing.orange.admin.module.system.permission.biz.converter;

import com.hzqing.orange.admin.module.system.permission.common.vo.Menu;
import com.hzqing.orange.admin.module.system.permission.common.vo.RouterMeta;
import com.hzqing.orange.admin.module.system.permission.common.vo.RouterTree;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 *@author 程序员橙子
 */
@Mapper
public interface PermissionConverter {

    PermissionConverter INSTANCE = Mappers.getMapper(PermissionConverter.class);

    List<RouterTree> listMenusToRouters(List<Menu> menuList);


    default RouterTree toRoutersTreeVo(Menu menu) {
        if (menu == null) {
            return null;
        }

        RouterTree routerTree = new RouterTree();
        routerTree.setId(menu.getId());
        routerTree.setParentId(menu.getParentId());
        routerTree.setPath(menu.getPath());
        routerTree.setPermission(menu.getPermission());
        routerTree.setComponent(menu.getComponent());

        RouterMeta meta = new RouterMeta();
        meta.setTitle(menu.getName());
        meta.setIcon(menu.getIcon());
        meta.setHidden(menu.getHidden());
        routerTree.setMeta(meta);
        return routerTree;
    }
}
