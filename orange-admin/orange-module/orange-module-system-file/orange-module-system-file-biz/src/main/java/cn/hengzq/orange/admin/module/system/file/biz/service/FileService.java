package cn.hengzq.orange.admin.module.system.file.biz.service;

import cn.hengzq.orange.admin.module.system.file.common.vo.FileVO;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FileAllQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.query.FilePageQuery;
import cn.hengzq.orange.admin.module.system.file.common.vo.request.FileUploadRequest;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;

import java.util.List;

public interface FileService {
    PageVO<FileVO> page(FilePageQuery queryVo);

    List<FileVO> queryAll(FileAllQuery query);

    Boolean removeById(Long id);

    FileVO upload(FileUploadRequest request);
}
