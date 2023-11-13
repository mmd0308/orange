package cn.hengzq.orange.admin.module.system.info.biz.convert;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoTypeListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoTypeEntity;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoTypeVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypeAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoTypePageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoTypeAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface InfoTypeConverter extends Converter {

    InfoTypeConverter INSTANCE = Mappers.getMapper(InfoTypeConverter.class);

    InfoTypeEntity toEntity(InfoTypeVO vo);

    InfoTypeEntity toEntity(InfoTypeAddOrUpdateRequest request);

    InfoTypeVO toVo(InfoTypeEntity entity);

    InfoTypeListQuery toListQuery(InfoTypePageQuery queryVo);

    InfoTypeListQuery toListQuery(InfoTypeAllQuery query);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<InfoTypeVO> toPage(Page<InfoTypeEntity> page);


    default InfoTypeEntity updateConvert(InfoTypeEntity entity, InfoTypeAddOrUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (Objects.nonNull(request.getSort())) {
            entity.setSort(request.getSort());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        return entity;
    }

    List<InfoTypeVO> toListVo(List<InfoTypeEntity> entityList);

}
