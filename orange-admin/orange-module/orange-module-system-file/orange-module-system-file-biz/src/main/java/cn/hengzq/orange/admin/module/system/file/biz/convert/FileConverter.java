package cn.hengzq.orange.admin.module.system.file.biz.convert;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileEntity;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileAllQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FilePageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.starter.storage.dto.UploadResult;
import cn.hutool.core.io.file.FileNameUtil;
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
public interface FileConverter extends Converter {

    FileConverter INSTANCE = Mappers.getMapper(FileConverter.class);

    default FileEntity toEntity(long fileSize, long configId, String originalFilename,
                                UploadResult uploadResult) {
        FileEntity entity = new FileEntity();
        entity.setConfigId(configId);
        entity.setOriginalName(originalFilename);
        entity.setPath(uploadResult.getPath());
        entity.setName(uploadResult.getName());
        entity.setSize(fileSize);
        entity.setType(FileNameUtil.getSuffix(originalFilename));
        entity.setUrl(uploadResult.getUrl());
        entity.initAddParams();
        return entity;
    }

    FileEntity toEntity(FileVO vo);

    FileEntity toEntity(FileConfigAddOrUpdateRequest request);

    FileVO toVo(FileEntity entity);

    FileListQuery toListQuery(FilePageQuery queryVo);

    FileListQuery toListQuery(FileAllQuery query);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<FileVO> toPage(Page<FileEntity> page);


//    default InfoEntity updateConvert(InfoEntity entity, InfoAddOrUpdateRequest request) {
//        if (Objects.isNull(entity) || Objects.isNull(request)) {
//            return null;
//        }
//        if (Objects.nonNull(request.getTypeId())) {
//            entity.setTypeId(request.getTypeId());
//        }
//        if (StrUtil.isNotBlank(request.getTitle())) {
//            entity.setTitle(request.getTitle());
//        }
//        if (Objects.nonNull(request.getSort())) {
//            entity.setSort(request.getSort());
//        }
//        if (StrUtil.isNotBlank(request.getContent())) {
//            entity.setContent(request.getContent());
//        }
//        return entity;
//    }

    List<FileVO> toListVo(List<FileEntity> entityList);

}
