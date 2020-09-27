package com.leolab.zerosys.services.auth.springsecurity.provider;

import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.auth.springsecurity.authority.UrlGrantedAuthority;
import com.leolab.zerosys.services.auth.springsecurity.token.MBAuthenticationToken;
import com.leolab.zerosys.services.pm.dto.PermissionDTO;
import com.leolab.zerosys.services.pm.service.PermissionManageService;
import com.leolab.zerosys.services.uc.dto.UserDTO;
import com.leolab.zerosys.services.uc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 管理后台登录 提供者
 */
@Slf4j
public class MBLoginProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionManageService permissionManageService;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("MBLoginProvider start ...");

        //用户名密码验证
        R<UserDTO> userDTOR = userService.usernamePasswordVerify(String.valueOf(authentication.getPrincipal()), String.valueOf(authentication.getCredentials()));
        if (!userDTOR.isSuccess()) {
            throw new AuthenticationServiceException(userDTOR.getMsg());
        }

        UserDTO userDTO = userDTOR.getData();
        //权限列表
        List<PermissionDTO> permissionDTOList = permissionManageService.getInterfacePermissionListByUserId(userDTO.getId());
        List<UrlGrantedAuthority> urlGrantedAuthorities = permissionDTOList.stream()
                .map(item -> new UrlGrantedAuthority(item.getContent())).collect(Collectors.toList());
        //管理后台token
        MBAuthenticationToken mbAuthenticationToken = new MBAuthenticationToken(authentication.getPrincipal(),
                null, urlGrantedAuthorities);
        mbAuthenticationToken.setDetails(authentication.getDetails());
        return mbAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MBAuthenticationToken.class.isAssignableFrom(authentication);
    }

}