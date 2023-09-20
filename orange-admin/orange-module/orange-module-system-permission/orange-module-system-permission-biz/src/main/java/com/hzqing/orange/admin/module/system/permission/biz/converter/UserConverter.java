package com.hzqing.orange.admin.module.system.permission.biz.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.permission.biz.dto.UserListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.UserEntity;
import com.hzqing.orange.admin.module.system.permission.common.vo.UserDetailsVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.UserVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UserUpdateRequest;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Mapper
public interface UserConverter extends Converter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVO toVo(UserEntity entity);

    List<UserVO> toListVo(List<UserEntity> entityList);

    UserEntity toEntity(UserVO user);

    UserEntity toEntity(UserAddRequest request);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<UserVO> toPage(Page<UserEntity> page);

    UserListQuery toListQuery(UserPageQuery query);

    UserListQuery toListQuery(UserAllQuery query);

    UserEntity toEntity(UserUpdateRequest user);

    UserDetailsVO toDetailsVO(UserVO userVO);
}
