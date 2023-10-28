package cn.hengzq.starter.storage.constant;


import cn.hengzq.starter.storage.core.StorageConfig;
import cn.hengzq.starter.storage.core.StorageService;
import cn.hengzq.starter.storage.core.aliyun.AliyunStorageConfig;
import cn.hengzq.starter.storage.core.aliyun.AliyunStorageService;
import cn.hengzq.starter.storage.core.local.LocalStorageConfig;
import cn.hengzq.starter.storage.core.local.LocalStorageService;
import lombok.Getter;

import java.util.Locale;

/**
 * 存储器枚举
 *
 * @author 程序员橙子
 */
@Getter
public enum StorageEnum {

    /**
     * 本地
     */
    LOCAL(LocalStorageService.class, LocalStorageConfig.class, "本地存储"),

    /**
     * 阿里云
     */
    ALIYUN(AliyunStorageService.class, AliyunStorageConfig.class, "阿里云存储");



    private final Class<? extends StorageService> storageService;

    private final Class<? extends StorageConfig> storageConfig;

    private final String msg;

    StorageEnum(Class<? extends StorageService> storageService, Class<? extends StorageConfig> storageConfig, String msg) {
        this.storageService = storageService;
        this.storageConfig = storageConfig;
        this.msg = msg;
    }
    public static StorageEnum getByName(String name) {
        return valueOf(name.toUpperCase(Locale.ROOT));
    }
}
