package com.hzqing.orange.admin.starter.security.service.impl;

import com.hzqing.orange.admin.starter.security.service.PermissionAuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsManagerImpl implements UserDetailsService {

    private final PermissionAuthenticationService permissionAuthenticationService;

    public UserDetailsManagerImpl(PermissionAuthenticationService permissionAuthenticationService) {
        this.permissionAuthenticationService = permissionAuthenticationService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return permissionAuthenticationService.getByUsername(username);
    }
}
