package cn.hengzq.orange.admin.module.system.file.biz.convert;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileConfigListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.common.constant.StorageConfigConstant;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileConfigVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigAllQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigPageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.starter.common.convert.Converter;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.starter.storage.core.StorageConfig;
import cn.hengzq.starter.storage.core.aliyun.AliyunStorageConfig;
import cn.hengzq.starter.storage.core.local.LocalStorageConfig;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Mapper
public interface FileConfigConverter extends Converter {

    FileConfigConverter INSTANCE = Mappers.getMapper(FileConfigConverter.class);

    FileConfigEntity toEntity(FileConfigAddOrUpdateRequest request);

    FileConfigVO toVo(FileConfigEntity entity);

    FileConfigListQuery toListQuery(FileConfigPageQuery queryVo);

    FileConfigListQuery toListQuery(FileConfigAllQuery query);

    @Mappings({
            @Mapping(source = "size", target = "pageSize"),
            @Mapping(source = "current", target = "pageNo")
    })
    PageVO<FileConfigVO> toPage(Page<FileConfigEntity> page);

    List<FileConfigVO> toListVo(List<FileConfigEntity> entityList);

    default FileConfigEntity toUpdate(FileConfigEntity entity, FileConfigAddOrUpdateRequest request) {
        if (Objects.isNull(entity) || Objects.isNull(request)) {
            return null;
        }
        if (StrUtil.isNotBlank(request.getName())) {
            entity.setName(request.getName());
        }
        if (Objects.nonNull(request.getRemark())) {
            entity.setRemark(request.getRemark());
        }
        StorageConfig storageConfig = entity.getConfig();
        if (StrUtil.isNotBlank(request.getDomain())) {
            storageConfig.setDomain(request.getDomain());
        }
        if (storageConfig instanceof LocalStorageConfig config) {
            if (StrUtil.isNotBlank(request.getBasePath())) {
                config.setBasePath(request.getBasePath());
            }
        }
        if (storageConfig instanceof AliyunStorageConfig config) {
            if (StrUtil.isNotBlank(request.getEndPoint())) {
                config.setEndPoint(request.getEndPoint());
            }
            if (StrUtil.isNotBlank(request.getBucketName())) {
                config.setBucketName(request.getBucketName());
            }
            if (StrUtil.isNotBlank(request.getAccessKey())) {
                config.setAccessKey(request.getAccessKey());
            }
            if (StrUtil.isNotBlank(request.getAccessKeySecret())) {
                config.setAccessKeySecret(request.getAccessKeySecret());
            }
        }
        entity.setConfig(storageConfig);
        return entity;
    }

    default FileConfigEntity toAdd(FileConfigAddOrUpdateRequest request) {
        FileConfigEntity entity = toEntity(request);
        if (Objects.isNull(entity)) {
            return null;
        }
        // 获取配置类
        Class<? extends StorageConfig> storageConfigClass = request.getStorage().getStorageConfig();

        Map<String, String> configs = new HashMap<>(16);
        if (StrUtil.isNotBlank(request.getDomain())) {
            configs.put(StorageConfigConstant.DOMAIN, request.getDomain());
        }
        if (StrUtil.isNotBlank(request.getBasePath())) {
            configs.put(StorageConfigConstant.BASE_PATH, request.getBasePath());
        }

        if (StrUtil.isNotBlank(request.getEndPoint())) {
            configs.put(StorageConfigConstant.END_POINT, request.getEndPoint());
        }
        if (StrUtil.isNotBlank(request.getBucketName())) {
            configs.put(StorageConfigConstant.BUCKET_NAME, request.getBucketName());
        }
        if (StrUtil.isNotBlank(request.getAccessKey())) {
            configs.put(StorageConfigConstant.ACCESS_KEY, request.getAccessKey());
        }
        if (StrUtil.isNotBlank(request.getAccessKeySecret())) {
            configs.put(StorageConfigConstant.ACCESS_KEY_SECRET, request.getAccessKeySecret());
        }
        StorageConfig storageConfig = JSONUtil.toBean(JSONUtil.toJsonStr(configs), storageConfigClass);
        entity.setConfig(storageConfig);
        return entity;
    }
}
