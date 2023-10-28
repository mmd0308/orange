package cn.hengzq.orange.admin.module.system.file.biz.service.impl;

import cn.hengzq.orange.admin.module.system.file.biz.convert.FileConfigConverter;
import cn.hengzq.orange.admin.module.system.file.biz.dto.FileConfigListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileConfigManager;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileManager;
import cn.hengzq.orange.admin.module.system.file.biz.service.FileConfigService;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileConfigVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileStorageVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileConfigPageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileConfigAddOrUpdateRequest;
import cn.hengzq.orange.admin.module.system.permission.common.exception.support.UserErrorCode;
import cn.hengzq.orange.admin.starter.common.util.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.starter.storage.constant.StorageEnum;
import cn.hengzq.starter.storage.core.StorageConfig;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class FileConfigServiceImpl implements FileConfigService {

    private final FileConfigManager manager;

    private final FileManager fileManager;

    @Override
    public Long add(FileConfigAddOrUpdateRequest request) {
        // 获取配置类
        Class<? extends StorageConfig> storageConfigClass = request.getStorage().getStorageConfig();
        StorageConfig storageConfig = JSONUtil.toBean(JSONUtil.toJsonStr(request.getConfigs()), storageConfigClass);

        FileConfigEntity entity = FileConfigConverter.INSTANCE.toEntity(request);
        entity.setConfig(storageConfig);
        Long id = manager.add(entity);
        if (Objects.nonNull(request.getMaster()) && request.getMaster()) {
            cancelOtherDefaultConfig(id);
        }
        return id;
    }

    /**
     * 取消指定ID以为的所有的默认配置
     *
     * @param id 配置id
     */
    private void cancelOtherDefaultConfig(Long id) {
        List<FileConfigEntity> entityList = manager.listByParams(FileConfigListQuery.builder().master(Boolean.TRUE).build());
        if (CollUtil.isEmpty(entityList)) {
            return;
        }
        entityList = entityList.stream().filter(item -> !item.getId().equals(id)).collect(Collectors.toList());
        if (CollUtil.isEmpty(entityList)) {
            return;
        }
        entityList.forEach(item -> {
            item.setMaster(Boolean.FALSE);
            manager.updateById(item);
        });
    }

    @Override
    public Boolean updateMaster(Long id) {
        FileConfigEntity entity = manager.getById(id);
        Assert.nonNull(entity, UserErrorCode.GLOBAL_DATA_NOT_EXIST);

        cancelOtherDefaultConfig(id);

        entity.setMaster(Boolean.TRUE);
        manager.updateById(entity);
        return Boolean.TRUE;
    }

    @Override
    public Boolean removeById(Long id) {
        FileConfigEntity entity = manager.getById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        return manager.removeById(id);
    }

    @Override
    public PageVO<FileConfigVO> page(FileConfigPageQuery query) {
        FileConfigListQuery listQuery = FileConfigConverter.INSTANCE.toListQuery(query);
        Page<FileConfigEntity> page = manager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return FileConfigConverter.INSTANCE.toPage(page);
    }

    @Override
    public FileConfigVO getDefaultConfig() {
        List<FileConfigEntity> entityList = manager.listByParams(FileConfigListQuery.builder().master(Boolean.TRUE).build());
        if (CollUtil.isEmpty(entityList)) {
            return null;
        }
        return FileConfigConverter.INSTANCE.toVo(entityList.get(0));
    }

    @Override
    public List<FileStorageVO> queryAllStorages() {
        StorageEnum[] values = StorageEnum.values();
        List<FileStorageVO> storageTypeList = new ArrayList<>(values.length);
        for (StorageEnum value : values) {
            storageTypeList.add(new FileStorageVO(value.name(), value.getMsg()));
        }
        return storageTypeList;
    }


}
