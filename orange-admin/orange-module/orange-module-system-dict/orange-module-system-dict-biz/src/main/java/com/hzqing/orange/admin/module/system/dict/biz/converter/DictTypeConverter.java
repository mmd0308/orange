package com.hzqing.orange.admin.module.system.dict.biz.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictTypeListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import com.hzqing.orange.admin.module.system.dict.common.vo.DictTypeVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import com.hzqing.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Mapper
public interface DictTypeConverter extends Converter {
    DictTypeConverter INSTANCE = Mappers.getMapper(DictTypeConverter.class);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<DictTypeVO> toPage(Page<DictTypeEntity> page);

    DictTypeVO toVo(DictTypeEntity entity);

    DictTypeEntity toEntity(DictTypeVO dictTypeVO);

    DictTypeListQuery toListQuery(DictTypePageQuery queryVo);

    DictTypeEntity toEntity(DictTypeAddRequest request);

    default DictTypeEntity updateConvert(DictTypeEntity entity, DictTypeUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (Objects.nonNull(request.getStatus())) {
            entity.setStatus(request.getStatus());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }
}
