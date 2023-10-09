package cn.hengzq.orange.admin.starter.webmvc.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 程序员橙子
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

}
