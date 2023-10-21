package cn.hengzq.orange.admin.module.system.permission.biz.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.MenuEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.MenuManager;
import cn.hengzq.orange.admin.module.system.permission.biz.mapper.MenuMapper;
import cn.hengzq.orange.admin.starter.mybatis.manager.impl.BaseManagerImpl;
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
public class MenuManagerImpl extends BaseManagerImpl<MenuMapper, MenuEntity> implements MenuManager {

    private final MenuMapper mapper;

    @Override
    public MenuMapper getMapper() {
        return this.mapper;
    }

    @Override
    public List<MenuEntity> listByParams(MenuListQuery query) {
        return mapper.selectList(Wrappers.<MenuEntity>lambdaQuery()
                .eq(Objects.nonNull(query.getParentId()), MenuEntity::getParentId, query.getParentId())
                .eq(Objects.nonNull(query.getPermission()), MenuEntity::getPermission, query.getPermission())
                .eq(StrUtil.isNotBlank(query.getName()), MenuEntity::getName, query.getName())
                .eq(Objects.nonNull(query.getHidden()), MenuEntity::getHidden, query.getHidden())
                .like(StrUtil.isNotBlank(query.getNameLike()), MenuEntity::getName, query.getNameLike())
                .like(StrUtil.isNotBlank(query.getPermissionLike()), MenuEntity::getPermission, query.getPermissionLike())
                .orderByAsc(MenuEntity::getSort)
        );
    }
}
