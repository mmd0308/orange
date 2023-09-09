package com.hzqing.orange.admin.module.system.permission.biz.api;

import com.hzqing.orange.admin.module.system.permission.api.PermissionApi;
import com.hzqing.orange.admin.module.system.permission.common.vo.ResourcePermissions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionApiImpl implements PermissionApi {

    @Override
    public ResourcePermissions queryAllPermissionsByUserId(Long userId) {
        return null;
    }
}
