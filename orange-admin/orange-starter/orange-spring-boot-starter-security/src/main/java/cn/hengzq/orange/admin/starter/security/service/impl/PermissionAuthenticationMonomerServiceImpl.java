package cn.hengzq.orange.admin.starter.security.service.impl;

import cn.hengzq.orange.admin.module.system.permission.api.PermissionApi;
import cn.hengzq.orange.admin.module.system.permission.api.UserApi;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import cn.hengzq.orange.admin.starter.security.dto.LoginUser;
import cn.hengzq.orange.admin.starter.security.service.PermissionAuthenticationService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class PermissionAuthenticationMonomerServiceImpl implements PermissionAuthenticationService {

    private final UserApi userApi;
    private final PermissionApi permissionApi;

    public PermissionAuthenticationMonomerServiceImpl(UserApi userApi, PermissionApi permissionApi) {
        this.userApi = userApi;
        this.permissionApi = permissionApi;
    }

    @Override
    public LoginUser getByUsername(String username) {
        UserVO userVO = userApi.getByUsername(username);
        return LoginUser.builder()
                .id(userVO.getId())
                .username(userVO.getUsername())
                .password(userVO.getPassword())
                .build();
    }

    @Override
    public List<String> queryCurrentLoginUserPermissions() {
        Long userId = GlobalContextHelper.getCurrentUserId();
        if (Objects.isNull(userId)) {
            log.warn("userId is null.");
            return Collections.emptyList();
        }
        ResourcePermissions resourcePermissions = permissionApi.queryAllPermissionsByUserId(userId);
        return resourcePermissions.getAllPermissions();
    }
}
