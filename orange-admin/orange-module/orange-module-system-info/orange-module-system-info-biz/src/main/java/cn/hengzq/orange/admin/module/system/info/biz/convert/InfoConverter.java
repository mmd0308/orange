package cn.hengzq.orange.admin.module.system.info.biz.convert;

import cn.hengzq.orange.admin.module.system.info.biz.dto.InfoListQuery;
import cn.hengzq.orange.admin.module.system.info.biz.entity.InfoEntity;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoSimpleVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.InfoVO;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoAllQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.query.InfoPageQuery;
import cn.hengzq.orange.admin.module.system.info.common.vo.request.InfoAddOrUpdateRequest;
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
public interface InfoConverter extends Converter {

    InfoConverter INSTANCE = Mappers.getMapper(InfoConverter.class);

    InfoEntity toEntity(InfoVO vo);

    InfoEntity toEntity(InfoAddOrUpdateRequest request);

    InfoVO toVo(InfoEntity entity);

    InfoListQuery toListQuery(InfoPageQuery queryVo);

    InfoListQuery toListQuery(InfoAllQuery query);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<InfoSimpleVO> toPage(Page<InfoEntity> page);


    default InfoEntity updateConvert(InfoEntity entity, InfoAddOrUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (Objects.nonNull(request.getTypeId())) {
            entity.setTypeId(request.getTypeId());
        }
        if (StrUtil.isNotBlank(request.getTitle())) {
            entity.setTitle(request.getTitle());
        }
        if (Objects.nonNull(request.getSort())) {
            entity.setSort(request.getSort());
        }
        if (StrUtil.isNotBlank(request.getContent())) {
            entity.setContent(request.getContent());
        }
        return entity;
    }

    List<InfoVO> toListVo(List<InfoEntity> entityList);

    List<InfoSimpleVO> toSimpleListVo(List<InfoEntity> entityList);
}
