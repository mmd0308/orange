package cn.hengzq.orange.admin.starter.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hengzq.orange.admin.starter.common.constant.PermissionConstant;
import cn.hengzq.orange.admin.starter.security.service.PermissionAuthenticationService;
import cn.hengzq.orange.admin.starter.security.service.SecurityAuthenticationService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SecurityAuthenticationServiceImpl implements SecurityAuthenticationService {

    private final PermissionAuthenticationService permissionAuthenticationService;

    public SecurityAuthenticationServiceImpl(PermissionAuthenticationService permissionAuthenticationService) {
        this.permissionAuthenticationService = permissionAuthenticationService;
    }

    @Override
    public boolean hasPermission(String permission) {
        if (log.isDebugEnabled()) {
            log.debug("permission: {}", permission);
        }
        List<String> permissions = permissionAuthenticationService.queryCurrentLoginUserPermissions();
        if (CollUtil.isEmpty(permissions)) {
            return false;
        }
        // 管理员admin角色 拥有所有的权限
        if (permissions.contains(PermissionConstant.ADMIN_DEFAULT_PERMISSION)) {
            return true;
        }
        if (permissions.contains(permission)) {
            return true;
        }
        return false;
    }
}
