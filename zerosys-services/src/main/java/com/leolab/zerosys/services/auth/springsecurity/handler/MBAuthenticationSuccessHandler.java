package com.leolab.zerosys.services.auth.springsecurity.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leolab.zerosys.common.utils.R;
import com.leolab.zerosys.services.auth.accesstoken.AccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.AccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static com.leolab.zerosys.common.constant.CommonConstant.CONTENT_TYPE_JSON;

/**
 * 管理后台身份认证成功 处理者
 */
@Slf4j
public class MBAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("MBAuthenticationSuccessHandler start ...");

        //权限列表
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("权限列表: {}", authorities);

        AccessToken accessToken = accessTokenService.createAccessToken(authentication);

        response.setContentType(CONTENT_TYPE_JSON);
        response.getWriter().write(objectMapper.writeValueAsString(new R<>(accessToken)));
    }
}
