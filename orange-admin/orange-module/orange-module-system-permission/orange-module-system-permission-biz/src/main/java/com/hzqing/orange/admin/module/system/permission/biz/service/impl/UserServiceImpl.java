package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.orange.admin.module.system.permission.biz.converter.UserConverter;
import com.hzqing.orange.admin.module.system.permission.biz.dto.UserListQuery;
import com.hzqing.orange.admin.module.system.permission.biz.entity.UserEntity;
import com.hzqing.orange.admin.module.system.permission.biz.manager.UserManager;
import com.hzqing.orange.admin.module.system.permission.biz.service.DepartmentService;
import com.hzqing.orange.admin.module.system.permission.biz.service.UserService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.Department;
import com.hzqing.orange.admin.module.system.permission.common.vo.UserVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UpdatePasswordRequest;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import com.hzqing.orange.admin.starter.common.constants.CommonConstants;
import com.hzqing.orange.admin.starter.common.exception.ServiceException;
import com.hzqing.orange.admin.starter.common.util.CollUtils;
import com.hzqing.orange.admin.starter.common.validator.Assert;
import com.hzqing.orange.admin.starter.common.vo.PageVO;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserManager userManager;

    private final DepartmentService departmentService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserVO getByUsername(String username) {
        UserEntity entity = userManager.getByUsername(username);
        return UserConverter.INSTANCE.toVo(entity);
    }

    @Override
    public UserVO getById(Long userId) {
        UserEntity entity = userManager.getById(userId);
        return UserConverter.INSTANCE.toVo(entity);
    }

    @Override
    public Long add(UserAddRequest request) {
        // 密码加密
        String password = StrUtil.isBlank(request.getPassword()) ? CommonConstants.Common.DEFAULT_ADMIN_PASSWORD : request.getPassword();
        request.setPassword(passwordEncoder.encode(password));
        return userManager.add(UserConverter.INSTANCE.toEntity(request));
    }

    @Override
    public Boolean updatePassword(UpdatePasswordRequest request) {
        Long userId = Objects.isNull(request.getUserId()) ? GlobalContextHelper.getCurrentUserId() : request.getUserId();
        UserEntity entity = userManager.getById(userId);
        Assert.nonNull(entity, SystemPermissionErrorCode.GLOBAL_DATA_NOT_EXIST);
        boolean matches = passwordEncoder.matches(request.getNewPassword(), entity.getPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(SystemPermissionErrorCode.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return userManager.updateById(userId, entity);
    }

    @Override
    public PageVO<UserVO> page(UserPageQuery query) {
        UserListQuery listQuery = UserConverter.INSTANCE.toListQuery(query);

        if (Objects.nonNull(query.getDepartmentId()) && query.isIncludeSubsetDepartmentUsers()) {
            List<Long> departmentIds = getDepartmentIds(query.getDepartmentId());
            if (CollUtil.isEmpty(departmentIds)) {
                return new PageVO<>(query);
            }
            listQuery.setDepartmentIds(departmentIds);
            listQuery.setDepartmentId(null);
        }

        Page<UserEntity> page = userManager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return UserConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<UserVO> queryAll(UserAllQuery query) {
        UserListQuery listQuery = UserConverter.INSTANCE.toListQuery(query);

        if (Objects.nonNull(query.getDepartmentId()) && query.isIncludeSubsetDepartmentUsers()) {
            List<Long> departmentIds = getDepartmentIds(query.getDepartmentId());
            if (CollUtil.isEmpty(departmentIds)) {
                return Collections.emptyList();
            }
            listQuery.setDepartmentIds(departmentIds);
            listQuery.setDepartmentId(null);
        }

        List<UserEntity> entityList = userManager.listByParams(listQuery);
        return UserConverter.INSTANCE.toListVo(entityList);
    }

    private List<Long> getDepartmentIds(Long departmentId) {
        List<Department> departmentList = departmentService.querySelfAndSubsetById(departmentId);
        if (CollUtil.isEmpty(departmentList)) {
            return Collections.emptyList();
        }
        return CollUtils.convertList(departmentList, Department::getId);
    }
}
