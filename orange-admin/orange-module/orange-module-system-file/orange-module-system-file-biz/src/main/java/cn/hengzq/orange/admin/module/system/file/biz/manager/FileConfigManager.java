package cn.hengzq.orange.admin.module.system.file.biz.manager;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileConfigListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.dto.FileListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.BaseManager;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;


/**
 * @author 程序员橙子
 */
public interface FileConfigManager extends BaseManager<FileConfigEntity> {

    Page<FileConfigEntity> page(Integer pageNo, Integer pageSize, FileConfigListQuery query);

    List<FileConfigEntity> listByParams(FileConfigListQuery query);

    /**
     * 获取默认的配置
     *
     * @return 默认配置
     */
    FileConfigEntity getDefaultConfig();
}
