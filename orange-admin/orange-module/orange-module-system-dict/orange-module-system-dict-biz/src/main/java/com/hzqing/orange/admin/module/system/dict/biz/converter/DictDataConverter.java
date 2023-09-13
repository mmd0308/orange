package com.hzqing.orange.admin.module.system.dict.biz.converter;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hzqing.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import com.hzqing.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import com.hzqing.orange.admin.module.system.dict.common.vo.DictDataVO;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictDataAllQuery;
import com.hzqing.orange.admin.module.system.dict.common.vo.query.DictDataPageQuery;
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
public interface DictDataConverter extends Converter {

    DictDataConverter INSTANCE = Mappers.getMapper(DictDataConverter.class);

    List<DictDataVO> toListVo(List<DictDataEntity> entityList);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<DictDataVO> toPage(IPage<DictDataEntity> page);

    DictDataEntity toEntity(DictDataVO dictDataVO);

    DictDataVO toVo(DictDataEntity entity);

    DictDataListQuery toListQuery(DictDataPageQuery query);

    DictDataListQuery toListQuery(DictDataAllQuery query);
}
