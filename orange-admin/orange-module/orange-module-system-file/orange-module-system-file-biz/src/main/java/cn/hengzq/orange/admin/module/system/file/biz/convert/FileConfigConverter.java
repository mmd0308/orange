package cn.hengzq.orange.admin.module.system.file.biz.convert;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileConfigListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileConfigVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigAllQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigPageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Mapper
public interface FileConfigConverter extends Converter {

    FileConfigConverter INSTANCE = Mappers.getMapper(FileConfigConverter.class);

    FileConfigEntity toEntity(FileConfigVO vo);

    FileConfigEntity toEntity(FileConfigAddOrUpdateRequest request);

    FileConfigVO toVo(FileConfigEntity entity);

    FileConfigListQuery toListQuery(FileConfigPageQuery queryVo);

    FileConfigListQuery toListQuery(FileConfigAllQuery query);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<FileConfigVO> toPage(Page<FileConfigEntity> page);


//    default InfoTypeEntity updateConvert(InfoTypeEntity entity, InfoTypeAddOrUpdateRequest request) {
//        if (Objects.isNull(entity) || Objects.isNull(request)) {
//            return null;
//        }
//        if (StrUtil.isNotBlank(request.getName())) {
//            entity.setName(request.getName());
//        }
//        if (Objects.nonNull(request.getSort())) {
//            entity.setSort(request.getSort());
//        }
//        if (Objects.nonNull(request.getRemark())) {
//            entity.setRemark(request.getRemark());
//        }
//        return entity;
//    }

    List<FileConfigVO> toListVo(List<FileConfigEntity> entityList);

}
