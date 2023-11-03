package cn.hengzq.starter.storage.core.aliyun;

import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.starter.storage.constant.StorageErrorCode;
import cn.hengzq.starter.storage.core.impl.AbstractStorageService;
import cn.hengzq.starter.storage.dto.UploadResult;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

/**
 * @author 程序员橙子
 */
@Slf4j
public class AliyunStorageService extends AbstractStorageService<AliyunStorageConfig> {

    public AliyunStorageService(Long id, AliyunStorageConfig config) {
        super(id, config);
    }

    @Override
    public UploadResult upload(byte[] content, String path, String filename) {
        OSS client = new OSSClientBuilder().build(config.getEndPoint(), config.getAccessKey(), config.getAccessKeySecret());
        try {
            client.putObject(config.getBucketName(), path + StrUtil.SLASH + filename, new ByteArrayInputStream(content));
        } catch (Exception e) {
            throw new ServiceException(StorageErrorCode.FILE_UPLOAD_FAILED);
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return UploadResult.builder()
                .url(config.getDomain() + StrUtil.SLASH + path + StrUtil.SLASH + filename)
                .name(filename)
                .path(path)
                .build();
    }

    @Override
    public byte[] getContent(String path) {
        return new byte[0];
    }

}
