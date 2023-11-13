package cn.hengzq.starter.storage.core.local;

import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.starter.storage.constant.StorageErrorCode;
import cn.hengzq.starter.storage.core.impl.AbstractStorageService;
import cn.hengzq.starter.storage.dto.UploadResult;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @author 程序员橙子
 */
@Slf4j
public class LocalStorageService extends AbstractStorageService<LocalStorageConfig> {


    public LocalStorageService(Long id, LocalStorageConfig config) {
        super(id, config);
    }

    @Override
    public UploadResult upload(byte[] content, String path, String filename) {
        try {
            String absolutePath = FileUtil.normalize(config.getBasePath() + File.separator + path);
            FileUtil.writeBytes(content, absolutePath);
            return UploadResult.builder()
                    .path(path)
                    .build();
        } catch (Exception e) {
            throw new ServiceException(StorageErrorCode.FILE_UPLOAD_FAILED);
        }
    }

    @Override
    public byte[] getContent(String path) {
        String filePath = getFilePath(path);
        return FileUtil.readBytes(filePath);
    }

    private String getFilePath(String path) {
        return config.getBasePath() + File.separator + path;
    }


}
