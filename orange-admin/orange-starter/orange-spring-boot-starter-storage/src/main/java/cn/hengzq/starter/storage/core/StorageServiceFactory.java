package cn.hengzq.starter.storage.core;

/**
 * @author 程序员橙子
 */
public interface StorageServiceFactory {

    /**
     * 获取存储服务
     *
     * @param configId 配置ID
     * @return
     */
    StorageService getStorageService(Long configId);

    /**
     * 刷新配置
     *
     * @param configId
     * @param storage
     * @param config
     * @param <Config>
     */
    <Config extends StorageConfig> void refresh(Long configId, String storage, Config config);

    /**
     * 生成新的存储服务
     *
     * @param configId 配置ID
     * @param storage  存储方式
     * @param config   存储配置
     * @return 返回存储服务
     */
    StorageService generateStorageService(Long configId, String storage, StorageConfig config);
}
