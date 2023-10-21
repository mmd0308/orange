package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.UserListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserDetailsVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

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

    default UserEntity updateConvert(UserEntity entity, UserUpdateRequest request) {
        if (Objects.isNull(entity)) {
            return null;
        }
        if (Objects.nonNull(request.getDepartmentId())) {
            entity.setDepartmentId(request.getDepartmentId());
        }
        if (Objects.nonNull(request.getAvatar())) {
            entity.setAvatar(request.getAvatar());
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (StrUtil.isNotBlank(request.getEmail())) {
            entity.setEmail(request.getEmail());
        }
        if (Objects.nonNull((request.getSex()))) {
            entity.setSex(request.getSex());
        }
        if (StrUtil.isNotBlank(request.getPhone())) {
            entity.setPhone(request.getPhone());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }
}
