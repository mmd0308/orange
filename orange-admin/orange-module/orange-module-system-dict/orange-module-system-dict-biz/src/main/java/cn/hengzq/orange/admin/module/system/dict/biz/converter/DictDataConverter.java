package cn.hengzq.orange.admin.module.system.dict.biz.converter;


import com.baomidou.mybatisplus.core.metadata.IPage;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictDataListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictDataEntity;
import cn.hengzq.orange.admin.module.system.dict.common.vo.DictDataVO;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictDataAllQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictDataPageQuery;
import cn.hengzq.orange.admin.starter.common.converter.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
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
