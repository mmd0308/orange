package cn.hengzq.starter.storage.core.impl;

import cn.hengzq.starter.storage.constant.StorageEnum;
import cn.hengzq.starter.storage.core.StorageConfig;
import cn.hengzq.starter.storage.core.StorageService;
import cn.hengzq.starter.storage.core.StorageServiceFactory;
import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 程序员橙子
 */
@Slf4j
public class StorageServiceFactoryImpl implements StorageServiceFactory {

    /**
     * 存储服务进行缓存
     */
    private final ConcurrentHashMap<Long, StorageService> storageServiceMap = new ConcurrentHashMap();

    @Override
    public StorageService getStorageService(Long configId) {
        StorageService storageService = storageServiceMap.get(configId);
        if (storageService == null) {
            log.error("storageService is not exist. configId:{}", configId);
        }
        return storageService;
    }

    @Override
    public <Config extends StorageConfig> void refresh(Long configId, String storage, Config config) {
        StorageService storageService = storageServiceMap.get(configId);
        if (storageService == null) {
            storageService = this.createStorageService(configId, storage, config);
            storageServiceMap.put(configId, storageService);
        } else {
//            storageService.refresh(config);
        }
    }

    @Override
    public StorageService generateStorageService(Long configId, String storage, StorageConfig config) {
        return this.createStorageService(configId, storage, config);
    }

    private <Config extends StorageConfig> StorageService createStorageService(Long configId, String storage, Config config) {
        StorageEnum storageType = StorageEnum.getByName(storage);
        if (Objects.isNull(storageType)) {
            return null;
        }
        // 创建客户端
        return ReflectUtil.newInstance(storageType.getStorageService(), configId, config);
    }
}
