package com.hzqing.orange.admin.module.system.permission.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.hzqing.orange.admin.module.system.permission.biz.service.AuthService;
import com.hzqing.orange.admin.module.system.permission.biz.service.RoleService;
import com.hzqing.orange.admin.module.system.permission.biz.service.UserService;
import com.hzqing.orange.admin.module.system.permission.common.constants.SystemPermissionErrorCode;
import com.hzqing.orange.admin.module.system.permission.common.vo.Role;
import com.hzqing.orange.admin.module.system.permission.common.vo.Token;
import com.hzqing.orange.admin.module.system.permission.common.vo.UserInfo;
import com.hzqing.orange.admin.module.system.permission.common.vo.UserVO;
import com.hzqing.orange.admin.module.system.permission.common.vo.request.LoginRequest;
import com.hzqing.orange.admin.starter.common.exception.ServiceException;
import com.hzqing.orange.admin.starter.common.util.CollUtils;
import com.hzqing.orange.admin.starter.context.GlobalContextHelper;
import com.hzqing.orange.admin.starter.security.util.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 程序员橙子
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final static String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAOqITyWTbOVaoX+JZe6q3bTcmNcnfHwj/AzNrdYR+wCno6esdCGqJWLviTle3wwl9TOsyBimDy7gQph+l3" +
            "+vQEQT6Xcmy1x9Jez63ZH2O97EDS7IZ3/5g9jq81JqFCDd/aMJiFn2+nsHEXRdJkIj459KVbUI9+fJRTKn1KI3IlOnAgMBAAECgYAQhq5dDXhc6Pf8Tqg6GxwoRGsYrhWdgcOe+1dGgFnZf/aOOB6uyJh90jcvLbo11u7iR0iKRBlJyk+nAzSVsLmsb9K0wfwovoQlZP4RAK1ET3RP3fLEJtG6WYSpbncVSs8okSrrDUE9hNxV8UtW6Y145liEIxH15lguHf4Jyil0MQJBAP+H8PZhmC6IvJ7jldtKmfd/EByF2F/kFhTfh4nBnGqrMeMyIBm8bCOasFSsOy6OZTFy9dii3ri99vwk+L3GY/MCQQDq9oB/FJPuG3mxRAvrJG+qe8C/6fblPpLfAHLIWISRO2peWt7Ud6r9FUQb6zbqcj/7wliLrx6OeQQdbkKvneJ9AkEApqQNpW+B4h7z+x5qFQdyny+y3xb+Q5KoP9aCOnkTu5CHSSXgP0hcsV9ozN9A/RyJq5TP9QZJ/uqLjmXB/WjKtwJBALjQLaBHslf+uoipSmqpnT/O2Xza7f3Ba0sHEkHuBlAqGO+gsFcUzaUF/i2rpOVh+lvvsTAmDXXpUEhJ+yAhow0CQQD83srI9BbYuYM+uG78BWNxAg9/NQCzX4r3xiSDlfd+ouaKS/jkEohXvX8rEvFYre/+3LRA4jJRm4RHmwpkJwnq";

    private final static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDqiE8lk2zlWqF/iWXuqt203JjXJ3x8I/wMza3WEfsAp6OnrHQhqiVi74k5Xt8MJfUzrMgYpg8u4EKYfpd/r0BEE+l3JstcfSXs" +
            "+t2R9jvexA0uyGd/+YPY6vNSahQg3f2jCYhZ9vp7BxF0XSZCI+OfSlW1CPfnyUUyp9SiNyJTpwIDAQAB";

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final RoleService roleService;

    @Override
    public Token login(LoginRequest params) {
        GlobalContextHelper.setContext(params.getTenantId());
        UserVO user = userService.getByUsername(params.getUsername());
        if (user == null) {
            log.error("account is error. account:{}", params.getUsername());
            throw new ServiceException(SystemPermissionErrorCode.GLOBAL_AUTH_ACCOUNT_ERROR);
        }
        RSA rsa = new RSA(PRIVATE_KEY, null);
        String password = rsa.decryptStr(params.getPassword(), KeyType.PrivateKey);
        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (!matches) {
            log.error("password is error.");
            throw new ServiceException(SystemPermissionErrorCode.GLOBAL_AUTH_PASSWORD_ERROR);
        }
        GlobalContextHelper.setContext(params.getTenantId(), user.getId(), user.getUsername());
        return new Token(JWTUtil.createToken(user.getId(), user.getUsername(), params.getTenantId()));
    }


    @Override
    public UserInfo getInfo() {
        Long currentUserId = GlobalContextHelper.getCurrentUserId();
        UserVO user = userService.getById(currentUserId);
        List<Role> roleList = roleService.queryByUserId(currentUserId);
        return UserInfo.builder().user(user)
                .rolePermissionList(CollUtils.convertList(roleList, Role::getPermission))
                .build();
    }

    @Override
    public String passwordEncrypt(String password) {
        if (StrUtil.isBlank(password)) {
            return null;
        }
        RSA rsa = new RSA(null, PUBLIC_KEY);
        return rsa.encryptBase64(password, KeyType.PublicKey);
    }


//    private void getPermissions(List<RoleEntity> roles, List<String> menuPermissionList, List<String> buttonPermissionList) {
//        if (CollectionUtils.isEmpty(roles)) {
//            log.info("roles is empty.");
//            return;
//        }
//        List<RoleResourceRlEntity> roleResourceRlEntityList = roleResourceRlMapper.queryByRoleIds(CollUtils.convertList(roles, RoleEntity::getId));
//        if (CollUtil.isEmpty(roleResourceRlEntityList)) {
//            return;
//        }
//        List<Long> menuIds = roleResourceRlEntityList.stream().filter(item -> ResourceTypeEnum.MENU.equals(item.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
//        if (CollUtil.isNotEmpty(menuIds)) {
//            List<MenuEntity> menuEntityList = menuMapper.selectBatchIds(menuIds);
//            if (CollUtil.isNotEmpty(menuEntityList)) {
//                menuPermissionList.addAll(CollUtils.convertList(menuEntityList, MenuEntity::getPermission));
//            }
//        }
//        List<Long> buttonIds = roleResourceRlEntityList.stream().filter(item -> ResourceTypeEnum.BUTTON.equals(item.getResourceType())).map(RoleResourceRlEntity::getResourceId).toList();
//        if (CollUtil.isNotEmpty(buttonIds)) {
//            List<ButtonEntity> buttonEntityList = buttonMapper.selectBatchIds(buttonIds);
//            if (CollUtil.isNotEmpty(buttonEntityList)) {
//                buttonPermissionList.addAll(CollUtils.convertList(buttonEntityList, ButtonEntity::getPermission));
//            }
//        }
//    }
//
//
}
