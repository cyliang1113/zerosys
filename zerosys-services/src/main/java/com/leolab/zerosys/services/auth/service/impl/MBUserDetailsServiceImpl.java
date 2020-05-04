package com.leolab.zerosys.services.auth.service.impl;

import com.leolab.zerosys.common.constant.FailMsgEnum;
import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.service.PermissionManageService;
import com.leolab.zerosys.services.pm.service.UserRoleService;
import com.leolab.zerosys.services.uc.dto.UserDTO;
import com.leolab.zerosys.services.uc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * 管理后台 UserDetailsService
 */
public class MBUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionManageService permissionManageService;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userByUsername = userService.getUserByUsername(username);
        if (userByUsername == null) {
            throw new RuntimeException(FailMsgEnum.username_or_password_mistake.getMsg());
        }
        List<PermissionDTO> permissionList = permissionManageService.getInterfacePermissionListByUserId(userByUsername.getId());

        return null;
    }
}
