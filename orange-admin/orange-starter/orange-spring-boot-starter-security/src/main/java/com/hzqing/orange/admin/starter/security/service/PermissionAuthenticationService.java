package com.hzqing.orange.admin.starter.security.service;


import com.hzqing.orange.admin.starter.security.dto.LoginUser;

import java.util.List;

public interface PermissionAuthenticationService {


    LoginUser getByUsername(String username);

    /**
     * 查询当前登录用户的所有权限编码
     *
     * @return 返回当前登陆人的权限编码
     */
    List<String> queryCurrentLoginUserPermissions();

}
