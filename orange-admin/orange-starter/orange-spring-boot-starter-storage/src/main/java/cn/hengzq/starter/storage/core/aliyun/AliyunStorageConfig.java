package cn.hengzq.starter.storage.core.aliyun;

import cn.hengzq.starter.storage.core.StorageConfig;
import lombok.Data;


/**
 * @author 程序员橙子
 */
@Data
public class AliyunStorageConfig implements StorageConfig {

    /**
     * Bucket 域名
     * https://help.aliyun.com/zh/oss/user-guide/oss-domain-names?spm=a2c4g.11186623.0.0.1c9b3a74mRtsH9#p-353-e57-vgs
     */
    private String domain;

    /**
     * Endpoint（地域节点）
     * eg:oss-cn-beijing.aliyuncs.com
     */
    private String endPoint;

    /**
     * Bucket 名称
     */
    private String bucketName;

    /**
     * 查看AccessKey https://ram.console.aliyun.com/manage/ak
     */
    private String accessKey;

    private String accessKeySecret;


    @Override
    public String getDomain() {
        return this.domain;
    }
}
