package cn.hengzq.orange.admin.module.system.file.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileEntity;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileManager;
import cn.hengzq.orange.admin.module.system.file.biz.mapper.FileMapper;
import cn.hengzq.orange.admin.starter.mybatis.entity.BaseEntity;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class FileManagerImpl extends BaseManagerImpl<FileMapper, FileEntity> implements FileManager {

    private final FileMapper mapper;

    @Override
    public FileMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<FileEntity> page(Integer pageNo, Integer pageSize, FileListQuery query) {
        LambdaQueryWrapper<FileEntity> queryWrapper = getQueryWrapper(query);
        return mapper.selectPage(new Page<FileEntity>(pageNo, pageSize), queryWrapper);
    }

    private static LambdaQueryWrapper<FileEntity> getQueryWrapper(FileListQuery query) {
        return Wrappers.<FileEntity>lambdaQuery()
                .ge(Objects.nonNull(query.getCreatedStartTime()), FileEntity::getCreatedAt, query.getCreatedStartTime())
                .le(Objects.nonNull(query.getCreatedEndTime()), FileEntity::getCreatedAt, query.getCreatedEndTime())
                .in(CollUtil.isNotEmpty(query.getIds()), FileEntity::getId, query.getIds())
                .orderByDesc(BaseEntity::getCreatedAt);
    }

    @Override
    public List<FileEntity> listByParams(FileListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }
}
