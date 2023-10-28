package cn.hengzq.orange.admin.module.system.file.biz.manager;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface FileManager extends BaseManager<FileEntity> {

    Page<FileEntity> page(Integer pageNo, Integer pageSize, FileListQuery query);

    List<FileEntity> listByParams(FileListQuery query);
}
