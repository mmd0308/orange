package cn.hengzq.orange.admin.module.system.permission.biz.service.impl;

import cn.hengzq.orange.admin.module.system.permission.biz.converter.UserConverter;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.UserListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.DepartmentEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.UserEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.DepartmentManager;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.UserManager;
import cn.hengzq.orange.admin.module.system.permission.biz.service.RoleService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.exception.SystemPermissionErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.exception.support.UserErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserDetailsVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserAllQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.UserPageQuery;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.ResetPasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UpdatePasswordRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserAddRequest;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.UserUpdateRequest;
import cn.hengzq.orange.admin.starter.common.constant.PermissionConstant;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.util.Assert;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.common.vo.PageVO;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private final UserManager manager;

    private final DepartmentManager departmentManager;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Override
    public UserVO getByUsername(String username) {
        UserEntity entity = manager.getByUsername(username);
        return UserConverter.INSTANCE.toVo(entity);
    }

    @Override
    public UserVO getById(Long userId) {
        UserEntity entity = manager.getById(userId);
        return UserConverter.INSTANCE.toVo(entity);
    }

    @Override
    public Long add(UserAddRequest request) {
        // 密码加密
        String password = StrUtil.isBlank(request.getPassword()) ? PermissionConstant.DEFAULT_USER_PASSWORD : request.getPassword();
        request.setPassword(passwordEncoder.encode(password));
        return manager.add(UserConverter.INSTANCE.toEntity(request));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Boolean updatePassword(UpdatePasswordRequest request) {
        Long userId = Objects.isNull(request.getUserId()) ? GlobalContextHelper.getCurrentUserId() : request.getUserId();
        UserEntity entity = manager.getById(userId);
        Assert.nonNull(entity, SystemPermissionErrorCode.GLOBAL_DATA_NOT_EXIST);
        boolean matches = passwordEncoder.matches(request.getNewPassword(), entity.getPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(SystemPermissionErrorCode.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return manager.updateById(userId, entity);
    }

    @Override
    public Boolean resetPassword(ResetPasswordRequest request) {
        request.check();
        UserEntity entity = manager.getById(request.getUserId());
        Assert.nonNull(entity, SystemPermissionErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity.setPassword(passwordEncoder.encode(request.getNewPassword()));
        return manager.updateById(entity);
    }

    @Override
    public PageVO<UserVO> page(UserPageQuery query) {
        UserListQuery listQuery = UserConverter.INSTANCE.toListQuery(query);
        if (Objects.nonNull(query.getDepartmentId())) {
            List<Long> departmentIds = getDepartmentIds(query.getDepartmentId());
            if (CollUtil.isEmpty(departmentIds)) {
                return new PageVO<>(query);
            }
            listQuery.setDepartmentIds(departmentIds);
            listQuery.setDepartmentId(null);
        }
        Page<UserEntity> page = manager.page(query.getPageNo(), query.getPageSize(), listQuery);
        return UserConverter.INSTANCE.toPage(page);
    }

    @Override
    public List<UserVO> queryAll(UserAllQuery query) {
        UserListQuery listQuery = UserConverter.INSTANCE.toListQuery(query);

        if (Objects.nonNull(query.getDepartmentId())) {
            List<Long> departmentIds = getDepartmentIds(query.getDepartmentId());
            if (CollUtil.isEmpty(departmentIds)) {
                return Collections.emptyList();
            }
            listQuery.setDepartmentIds(departmentIds);
            listQuery.setDepartmentId(null);
        }

        List<UserEntity> entityList = manager.listByParams(listQuery);
        return UserConverter.INSTANCE.toListVo(entityList);
    }

    @Override
    public UserDetailsVO getDetailsById(Long id) {
        UserVO userVO = getById(id);
        UserDetailsVO detailsVO = UserConverter.INSTANCE.toDetailsVO(userVO);
        if (Objects.isNull(detailsVO)) {
            return null;
        }
        detailsVO.setRoleVOList(roleService.queryByUserId(id));
        return detailsVO;
    }

    @Override
    public Boolean updateById(Long id, UserUpdateRequest request) {
        UserEntity entity = manager.getById(id);
        Assert.nonNull(entity, UserErrorCode.GLOBAL_DATA_NOT_EXIST);
        entity = UserConverter.INSTANCE.updateConvert(entity, request);
        return manager.updateById(entity);
    }

    @Override
    public List<UserVO> queryByIds(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            return List.of();
        }
        List<UserEntity> entityList = manager.listByIds(ids);
        return UserConverter.INSTANCE.toListVo(entityList);
    }

    private List<Long> getDepartmentIds(Long departmentId) {
        List<Long> departmentIds = new ArrayList<>();
        departmentIds.add(departmentId);
        List<DepartmentEntity> entityList = departmentManager.listSubsetByParentId(departmentId);
        if (CollUtil.isEmpty(entityList)) {
            return departmentIds;
        }
        departmentIds.addAll(CollUtils.convertList(entityList, DepartmentEntity::getId));
        return departmentIds;
    }
}
