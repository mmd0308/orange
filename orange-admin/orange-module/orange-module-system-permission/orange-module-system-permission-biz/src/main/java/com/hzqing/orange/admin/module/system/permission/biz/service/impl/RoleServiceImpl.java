package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  com.hzqing.orange.admin.module.system.permission.biz.converter.RoleConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.RoleEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.RoleManager;
import  com.hzqing.orange.admin.module.system.permission.biz.service.RoleService;
import com.hzqing.orange.admin.module.system.permission.common.constants.exception.RoleErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.Role;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.RolePageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.RoleUpdateRequest;
import com.hzqing.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import com.hzqing.orange.admin.starter.common.validator.Assert;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *@author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleManager roleManager;


    @Override
    public List<Role> queryByUserId(Long userId) {
        List<RoleEntity> entityList = roleManager.listByUserId(userId);
        return RoleConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public PageVO<Role> page(RolePageQuery query) {
        RoleListQuery listQuery = RoleConverter.INSTANCE.toListQuery(query);
        Page<RoleEntity> page = roleManager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return RoleConverter.INSTANCE.toPage(page);
    }

    @Override
    public Long add(RoleAddRequest request) {
        Assert.nonNull(request.getPermission(), RoleErrorCode.ROLE_PERMISSION_CANNOT_NULL);
        List<RoleEntity> entityList = roleManager.listByParams(RoleListQuery.builder().permission(request.getPermission()).build());
        Assert.isEmpty(entityList, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        RoleEntity entity = RoleConverter.INSTANCE.toEntity(request);
        return roleManager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, RoleUpdateRequest request) {
        RoleEntity entity = roleManager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstants.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(request.getPermission()) && !request.getPermission().equals(entity.getPermission())) {
            List<RoleEntity> entityList = roleManager.listByParams(RoleListQuery.builder().permission(request.getPermission()).build());
            Assert.isEmpty(entityList, RoleErrorCode.ROLE_PERMISSION_CANNOT_REPEAT);
        }
        entity = RoleConverter.INSTANCE.updateConvert(entity, request);
        return roleManager.updateById(entity);
    }
}
