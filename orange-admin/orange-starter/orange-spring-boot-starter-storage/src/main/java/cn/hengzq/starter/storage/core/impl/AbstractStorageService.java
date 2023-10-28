package cn.hengzq.starter.storage.core.impl;


import cn.hengzq.starter.storage.core.StorageConfig;
import cn.hengzq.starter.storage.core.StorageService;
import cn.hengzq.starter.storage.core.local.LocalStorageConfig;
import cn.hengzq.starter.storage.dto.UploadResult;
import cn.hengzq.starter.storage.util.StorageUtil;

/**
 * @author 程序员橙子
 */
public abstract class AbstractStorageService<Config extends StorageConfig> implements StorageService {

    /**
     * 配置编号
     */
    private final Long id;
    /**
     * 文件配置w
     */
    protected Config config;

    public AbstractStorageService(Long id, Config config) {
        this.id = id;
        this.config = config;
    }

    @Override
    public UploadResult upload(byte[] content, String filename) {
        String relativePath = StorageUtil.getRelativePath();
        if (config instanceof LocalStorageConfig) {
            relativePath = StorageUtil.getSystemRelativePath();
        }
        return upload(content, relativePath, StorageUtil.getNewFileName(filename));
    }
}
