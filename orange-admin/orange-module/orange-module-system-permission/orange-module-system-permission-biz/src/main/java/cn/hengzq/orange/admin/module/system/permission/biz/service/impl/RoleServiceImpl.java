package cn.hengzq.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  cn.hengzq.orange.admin.module.system.permission.biz.converter.RoleConverter;
import  cn.hengzq.orange.admin.module.system.permission.biz.dto.RoleListQuery;
import  cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import  cn.hengzq.orange.admin.module.system.permission.biz.manager.RoleManager;
import  cn.hengzq.orange.admin.module.system.permission.biz.service.RoleService;
import cn.hengzq.orange.admin.module.system.permission.common.constants.exception.RoleErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RoleVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.RolePageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.RoleAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.RoleUpdateRequest;
import cn.hengzq.orange.admin.starter.common.exception.GlobalErrorCodeConstants;
import cn.hengzq.orange.admin.starter.common.validator.Assert;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
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
    public List<RoleVO> queryByUserId(Long userId) {
        List<RoleEntity> entityList = roleManager.listByUserId(userId);
        return RoleConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public PageVO<RoleVO> page(RolePageQuery query) {
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
