package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hengzq.orange.admin.module.system.permission.common.vo.MenuVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RouterTreeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Mapper
public interface PermissionConverter {

    PermissionConverter INSTANCE = Mappers.getMapper(PermissionConverter.class);

    List<RouterTreeVO> listMenusToRouters(List<MenuVO> menuVOList);
}
