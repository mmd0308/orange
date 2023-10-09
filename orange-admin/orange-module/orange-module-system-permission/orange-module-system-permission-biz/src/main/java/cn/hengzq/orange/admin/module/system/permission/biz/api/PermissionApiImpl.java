package cn.hengzq.orange.admin.module.system.permission.biz.api;

import cn.hengzq.orange.admin.module.system.permission.api.PermissionApi;
import cn.hengzq.orange.admin.module.system.permission.biz.service.PermissionService;
import cn.hengzq.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionApiImpl implements PermissionApi {

    private final PermissionService permissionService;

    @Override
    public ResourcePermissions queryAllPermissionsByUserId(Long userId) {
        return permissionService.queryAllPermissionsByUserId(userId);
    }
}
