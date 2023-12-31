package cn.hengzq.orange.admin.module.system.record.biz.converter;

import cn.hengzq.orange.admin.module.system.record.biz.dto.RecordOperationListQuery;
import cn.hengzq.orange.admin.module.system.record.biz.entity.RecordOperationEntity;
import cn.hengzq.orange.admin.module.system.record.common.vo.RecordOperationVO;
import cn.hengzq.orange.admin.module.system.record.common.vo.query.OperationRecordPageQuery;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    RecordOperationListQuery toListQuery(OperationRecordPageQuery queryVo);
}
