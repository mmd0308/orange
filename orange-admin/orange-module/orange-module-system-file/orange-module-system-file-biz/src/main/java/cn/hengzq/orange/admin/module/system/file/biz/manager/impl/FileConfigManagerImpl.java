package cn.hengzq.orange.admin.module.system.file.biz.manager.impl;

import cn.hengzq.orange.admin.module.system.file.biz.dto.FileConfigListQuery;
import cn.hengzq.orange.admin.module.system.file.biz.entity.FileConfigEntity;
import cn.hengzq.orange.admin.module.system.file.biz.manager.FileConfigManager;
import cn.hengzq.orange.admin.module.system.file.biz.mapper.FileConfigMapper;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import cn.hutool.core.util.StrUtil;
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
public class FileConfigManagerImpl extends BaseManagerImpl<FileConfigMapper, FileConfigEntity>
        implements FileConfigManager {

    private final FileConfigMapper mapper;

    @Override
    public FileConfigMapper getMapper() {
        return this.mapper;
    }

    @Override
    public Page<FileConfigEntity> page(Integer pageNo, Integer pageSize, FileConfigListQuery query) {
        LambdaQueryWrapper<FileConfigEntity> queryWrapper = getQueryWrapper(query);
        return mapper.selectPage(new Page<FileConfigEntity>(pageNo, pageSize), queryWrapper);
    }

    private static LambdaQueryWrapper<FileConfigEntity> getQueryWrapper(FileConfigListQuery query) {
        return Wrappers.<FileConfigEntity>lambdaQuery()
                .eq(StrUtil.isNotBlank(query.getName()), FileConfigEntity::getName, query.getName())
                .eq(Objects.nonNull(query.getMaster()), FileConfigEntity::getMaster, query.getMaster())
                .like(StrUtil.isNotBlank(query.getNameLike()), FileConfigEntity::getName, query.getNameLike())
                .orderByDesc(FileConfigEntity::getCreatedAt);
    }

    @Override
    public List<FileConfigEntity> listByParams(FileConfigListQuery query) {
        return mapper.selectList(getQueryWrapper(query));
    }

    @Override
    public FileConfigEntity getDefaultConfig() {
        return mapper.selectOne(Wrappers.<FileConfigEntity>lambdaQuery().eq(FileConfigEntity::getMaster, Boolean.TRUE));
    }
}
