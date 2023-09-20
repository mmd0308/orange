package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import  com.hzqing.orange.admin.module.system.permission.biz.converter.ButtonConverter;
import  com.hzqing.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import  com.hzqing.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import  com.hzqing.orange.admin.module.system.permission.biz.manager.ButtonManager;
import  com.hzqing.orange.admin.module.system.permission.biz.service.ButtonService;
import com.hzqing.orange.admin.module.system.permission.common.constants.exception.ButtonErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.ButtonVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.ButtonPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonAddRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.ButtonUpdateRequest;
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
public class ButtonServiceImpl implements ButtonService {

    private final ButtonManager buttonManager;

    @Override
    public PageVO<ButtonVO> page(ButtonPageQuery queryVo) {
        ButtonListQuery listQuery = ButtonConverter.INSTANCE.toListQuery(queryVo);
        Page<ButtonEntity> page = buttonManager.page(queryVo.getPageNo(), queryVo.getPageSize(), listQuery);
        return ButtonConverter.INSTANCE.toPage(page);
    }

    @Override
    public Long add(ButtonAddRequest request) {
        Assert.nonNull(request.getPermission(), ButtonErrorCode.BUTTON_PERMISSION_CANNOT_NULL);
        List<ButtonEntity> entityList = buttonManager.listByParams(ButtonListQuery.builder().permission(request.getPermission()).build());
        Assert.isEmpty(entityList, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
        ButtonEntity entity = ButtonConverter.INSTANCE.toEntity(request);
        return buttonManager.add(entity);
    }

    @Override
    public Boolean updateById(Long id, ButtonUpdateRequest request) {
        ButtonEntity entity = buttonManager.getById(id);
        Assert.nonNull(entity.getId(), GlobalErrorCodeConstants.GLOBAL_DATA_NOT_EXIST);
        if (StrUtil.isNotBlank(request.getPermission()) && !request.getPermission().equals(entity.getPermission())) {
            List<ButtonEntity> entityList = buttonManager.listByParams(ButtonListQuery.builder().permission(request.getPermission()).build());
            Assert.isEmpty(entityList, ButtonErrorCode.BUTTON_PERMISSION_CANNOT_REPEAT);
        }
        entity = ButtonConverter.INSTANCE.updateConvert(entity, request);
        return buttonManager.updateById(entity);
    }

}
