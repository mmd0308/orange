package com.hzqing.orange.admin.module.system.permission.biz.converter;

import com.hzqing.orange.admin.module.system.permission.common.vo.MenuVO;
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

    List<RouterTree> listMenusToRouters(List<MenuVO> menuVOList);


    default RouterTree toRoutersTreeVo(MenuVO menuVO) {
        if (menuVO == null) {
            return null;
        }

        RouterTree routerTree = new RouterTree();
        routerTree.setId(menuVO.getId());
        routerTree.setParentId(menuVO.getParentId());
        routerTree.setPath(menuVO.getPath());
        routerTree.setPermission(menuVO.getPermission());
        routerTree.setComponent(menuVO.getComponent());

        RouterMeta meta = new RouterMeta();
        meta.setTitle(menuVO.getName());
        meta.setIcon(menuVO.getIcon());
        meta.setHidden(menuVO.getHidden());
        routerTree.setMeta(meta);
        return routerTree;
    }
}
