package com.hzqing.orange.admin.module.system.record.biz.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordOperationVO;
import com.hzqing.orange.admin.starter.common.converter.Converter;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author 程序员橙子
 */
@Mapper
public interface RecordOperationConverter extends Converter {
    RecordOperationConverter INSTANCE = Mappers.getMapper(RecordOperationConverter.class);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<RecordOperationVO> toPage(Page<RecordOperationEntity> page);

    RecordOperationVO toVo(RecordOperationEntity entity);

    RecordOperationEntity toEntity(RecordOperationVO recordOperationVO);
}
