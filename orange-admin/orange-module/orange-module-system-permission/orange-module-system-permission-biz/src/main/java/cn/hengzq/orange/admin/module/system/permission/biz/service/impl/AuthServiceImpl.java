package cn.hengzq.orange.admin.module.system.permission.biz.service.impl;

import cn.hengzq.orange.admin.module.system.permission.biz.dto.ButtonListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.dto.MenuListQuery;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.ButtonEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.MenuEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.entity.RoleResourceRlEntity;
import cn.hengzq.orange.admin.module.system.permission.biz.manager.*;
import cn.hengzq.orange.admin.module.system.permission.common.enums.ResourceTypeEnum;
import cn.hengzq.orange.admin.module.system.permission.common.vo.query.MenuAllQuery;
import cn.hengzq.orange.admin.starter.common.constant.PermissionConstant;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hengzq.orange.admin.module.system.permission.biz.service.AuthService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.RoleService;
import cn.hengzq.orange.admin.module.system.permission.biz.service.UserService;
import cn.hengzq.orange.admin.module.system.permission.common.exception.SystemPermissionErrorCode;
import cn.hengzq.orange.admin.module.system.permission.common.vo.RoleVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.Token;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserInfo;
import cn.hengzq.orange.admin.module.system.permission.common.vo.UserVO;
import cn.hengzq.orange.admin.module.system.permission.common.vo.request.LoginRequest;
import cn.hengzq.orange.admin.starter.common.exception.ServiceException;
import cn.hengzq.orange.admin.starter.common.util.CollUtils;
import cn.hengzq.orange.admin.starter.context.GlobalContextHelper;
import cn.hengzq.orange.admin.starter.security.util.JWTUtil;
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

    private final RoleManager roleManager;

    private final RoleResourceRlManager roleResourceRlManager;

    private final MenuManager menuManager;

    private final ButtonManager buttonManager;

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
        UserInfo.UserInfoBuilder userInfoBuilder = UserInfo.builder().user(user);
        // 获取用户角色
        List<RoleEntity> roleEntityList = roleManager.listByUserId(currentUserId);
        if (CollUtil.isEmpty(roleEntityList)) {
            return userInfoBuilder.build();
        }
        List<String> rolePermissions = CollUtils.convertList(roleEntityList, RoleEntity::getPermission);
        userInfoBuilder.rolePermissions(rolePermissions);

        // 超级管理员
        if (rolePermissions.contains(PermissionConstant.ADMIN_DEFAULT_PERMISSION)) {
            List<MenuEntity> menuEntityList = menuManager.listByParams(MenuListQuery.builder().build());
            userInfoBuilder.menuPermissions(CollUtils.convertList(menuEntityList, MenuEntity::getPermission));

            List<ButtonEntity> buttonEntityList = buttonManager.listByParams(ButtonListQuery.builder().build());
            userInfoBuilder.buttonPermissions(CollUtils.convertList(buttonEntityList, ButtonEntity::getPermission));
        } else {
            List<Long> roleIds = CollUtils.convertList(roleEntityList, RoleEntity::getId);
            List<RoleResourceRlEntity> roleResourceRlEntities = roleResourceRlManager.listByRoleIds(roleIds);

            List<Long> menuIds = roleResourceRlEntities.stream().filter(item -> ResourceTypeEnum.MENU.equals(item.getResourceType()))
                    .map(RoleResourceRlEntity::getResourceId).toList();
            // 获取菜单编码
            if (CollUtil.isNotEmpty(menuIds)) {
                List<MenuEntity> menuEntityList = menuManager.listByIds(menuIds);
                userInfoBuilder.menuPermissions(CollUtils.convertList(menuEntityList, MenuEntity::getPermission));
            }
            List<Long> buttonIds = roleResourceRlEntities.stream().filter(item -> ResourceTypeEnum.BUTTON.equals(item.getResourceType()))
                    .map(RoleResourceRlEntity::getResourceId).toList();
            // 获取按钮编码
            if (CollUtil.isNotEmpty(buttonIds)) {
                List<ButtonEntity> buttonEntities = buttonManager.listByIds(buttonIds);
                userInfoBuilder.buttonPermissions(CollUtils.convertList(buttonEntities, ButtonEntity::getPermission));
            }
        }

        return userInfoBuilder.build();
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
