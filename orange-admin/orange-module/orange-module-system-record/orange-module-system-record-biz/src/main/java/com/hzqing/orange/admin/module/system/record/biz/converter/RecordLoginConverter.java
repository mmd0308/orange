package com.hzqing.orange.admin.module.system.record.biz.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.record.biz.dto.RecordLoginListQuery;
import com.hzqing.orange.admin.module.system.record.biz.entity.RecordLoginEntity;
import com.hzqing.orange.admin.module.system.record.common.vo.RecordLoginVO;
import com.hzqing.orange.admin.module.system.record.common.vo.query.RecordLoginPageQuery;
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
public interface RecordLoginConverter extends Converter {
    RecordLoginConverter INSTANCE = Mappers.getMapper(RecordLoginConverter.class);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<RecordLoginVO> toPage(Page<RecordLoginEntity> page);

    RecordLoginVO toVo(RecordLoginEntity entity);

    RecordLoginEntity toEntity(RecordLoginVO RecordLoginVO);

    RecordLoginListQuery toListQuery(RecordLoginPageQuery query);
}
