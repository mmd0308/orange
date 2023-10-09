package cn.hengzq.orange.admin.module.system.permission.biz.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  cn.hengzq.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ButtonVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.ButtonPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ButtonAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ButtonUpdateRequest;
import cn.hengzq.orange.admin.starter.common.converter.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

/**
 *@author 程序员橙子
 */
@Mapper
public interface ButtonConverter extends Converter {

    ButtonConverter INSTANCE = Mappers.getMapper(ButtonConverter.class);

    ButtonEntity toEntity(ButtonVO buttonVO);

    ButtonEntity toEntity(ButtonAddRequest request);

    ButtonVO toVo(ButtonEntity entity);

    ButtonListQuery toListQuery(ButtonPageQuery queryVo);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<ButtonVO> toPage(Page<ButtonEntity> page);


    default ButtonEntity updateConvert(ButtonEntity entity, ButtonUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (StrUtil.isNotBlank(request.getPermission())) {
            entity.setPermission(request.getPermission());
        }
        if (Objects.nonNull(request.getSort())) {
            entity.setSort(request.getSort());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }

    List<ButtonVO> toListVo(List<ButtonEntity> buttonEntityList);

}
