package com.hzqing.orange.admin.starter.webmvc.convert;

//import com.hzqing.module.system.base.vo.user.UserVo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author 程序员橙子
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

//    List<BaseUser> listUserDtoToInfo(List<UserVo> data);
}
