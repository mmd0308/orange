package cn.hengzq.orange.admin.module.system.dict.biz.converter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hengzq.orange.admin.module.system.dict.biz.dto.DictTypeListQuery;
import cn.hengzq.orange.admin.module.system.dict.biz.entity.DictTypeEntity;
import cn.hengzq.orange.admin.module.system.dict.common.vo.DictTypeVO;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypeAllQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.query.DictTypePageQuery;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeAddRequest;
import cn.hengzq.orange.admin.module.system.dict.common.vo.request.DictTypeUpdateRequest;
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
public interface DictTypeConverter extends Converter {
    DictTypeConverter INSTANCE = Mappers.getMapper(DictTypeConverter.class);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<DictTypeVO> toPage(Page<DictTypeEntity> page);

    DictTypeVO toVO(DictTypeEntity entity);

    DictTypeEntity toEntity(DictTypeVO dictTypeVO);

    DictTypeListQuery toListQuery(DictTypePageQuery queryVo);

    DictTypeListQuery toListQuery(DictTypeAllQuery queryVo);


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

    List<DictTypeVO> toListVO(List<DictTypeEntity> entityList);

}
