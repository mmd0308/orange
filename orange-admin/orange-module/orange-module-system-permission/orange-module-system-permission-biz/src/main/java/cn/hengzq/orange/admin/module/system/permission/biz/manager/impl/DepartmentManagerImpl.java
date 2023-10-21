package cn.hengzq.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.DepartmentListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import cn.hengzq.orange.admin.module.system.permission.biz.mapper.DepartmentMapper;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
@Component
@AllArgsConstructor
public class DepartmentManagerImpl extends BaseManagerImpl<DepartmentMapper, DepartmentEntity> implements DepartmentManager {

    private final DepartmentMapper mapper;

    @Override
    public DepartmentMapper getMapper() {
        return this.mapper;
    }


    @Override
    public List<DepartmentEntity> listByParentId(Long parentId) {
        return mapper.selectList(Wrappers.<DepartmentEntity>lambdaQuery().eq(DepartmentEntity::getParentId, parentId));
    }

    @Override
    public List<DepartmentEntity> listByParentIds(List<Long> parentIds) {
        return mapper.selectList(Wrappers.<DepartmentEntity>lambdaQuery().in(DepartmentEntity::getParentId, parentIds));
    }

    @Override
    public List<DepartmentEntity> listByParams(DepartmentListQuery query) {
        LambdaQueryWrapper<DepartmentEntity> queryWrapper = Wrappers.<DepartmentEntity>lambdaQuery()
                .eq(Objects.nonNull(query.getParentId()), DepartmentEntity::getParentId, query.getParentId())
                .eq(StrUtil.isNotBlank(query.getName()), DepartmentEntity::getName, query.getName())
                .like(StrUtil.isNotBlank(query.getNameLike()), DepartmentEntity::getName, query.getNameLike())
                .in(CollUtil.isNotEmpty(query.getIds()), DepartmentEntity::getId, query.getIds())
                .orderByAsc(DepartmentEntity::getSort);
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<DepartmentEntity> listSubsetByParentId(Long parentId) {
        List<Long> parentIds = List.of(parentId);
        List<DepartmentEntity> entityList = new ArrayList<>();
        for (int i = 0; i < Short.MAX_VALUE; i++) {
            List<DepartmentEntity> tempList = this.listByParentIds(parentIds);
            if (CollUtil.isEmpty(tempList)) {
                break;
            }
            entityList.addAll(tempList);
            parentIds = CollUtils.convertList(tempList, DepartmentEntity::getId);
        }
        return entityList;
    }
}
