package cn.hengzq.orange.admin.module.system.file.biz.service.impl;

import cn.hengzq.orange.admin.module.system.file.biz.convert.FileConverter;
import cn.hengzq.orange.admin.module.system.file.biz.dto.FileListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileEntity;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileConfigManager;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileManager;
import cn.hengzq.orange.admin.module.system.file.biz.service.FileService;
import cn.hengzq.orange.admin.module.system.file.common.vo.FileVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileAllQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FilePageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileUploadRequest;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.starter.storage.core.StorageService;
import cn.hengzq.starter.storage.core.StorageServiceFactory;
import cn.hengzq.starter.storage.dto.UploadResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileManager manager;

    private final FileConfigManager configManager;

    private final StorageServiceFactory storageServiceFactory;


    @Override
    public PageVO<FileVO> page(FilePageQuery queryVo) {
        FileListQuery listQuery = FileConverter.INSTANCE.toListQuery(queryVo);
        Page<FileEntity> page = manager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        return FileConverter.INSTANCE.toPage(page);
    }


    @Override
    public List<FileVO> queryAll(FileAllQuery query) {
        List<FileEntity> entityList = manager.listByParams(FileConverter.INSTANCE.toListQuery(query));
        return FileConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public Boolean removeById(Long id) {
        FileEntity entity = manager.getById(id);
        if (Objects.isNull(entity)) {
            return Boolean.TRUE;
        }
        return manager.removeById(id);
    }

    @Override
    public FileVO upload(FileUploadRequest request) {
        FileConfigEntity config = configManager.getDefaultConfig();
        StorageService storageService = storageServiceFactory.generateStorageService(config.getId(), config.getStorage(), config.getConfig());
        try {
            MultipartFile file = request.getFile();
            String originalName = file.getOriginalFilename();
            UploadResult uploadResult = storageService.upload(file.getBytes(), originalName);
            FileEntity entity = FileConverter.INSTANCE.toEntity(file.getSize(), config.getId(), originalName, uploadResult);
            manager.add(entity);
            return FileConverter.INSTANCE.toVo(entity);
        } catch (IOException e) {
            throw new ServiceException(null);
        }
    }
}
