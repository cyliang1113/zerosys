package com.leolab.zerosys.services.auth.springsecurity.filter;

import com.leolab.zerosys.common.constant.FailMsgEnum;
import com.leolab.zerosys.services.auth.springsecurity.token.MBAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理后台登录(身份验证)Filter
 */
public class MBLoginFilter extends AbstractAuthenticationProcessingFilter {
    public static final String M_B_LOGIN_URL = "/mb/login"; //管理后台登录url
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    public MBLoginFilter() {
        super(new AntPathRequestMatcher(M_B_LOGIN_URL));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        logger.info("MBLoginFilter start ...");

//        if (!request.getMethod().equals(HttpMethod.POST.name())) {
//            throw new AuthenticationServiceException(
//                    "MBLogin request method not supported: " + request.getMethod());
//        }

        String username = request.getParameter(USERNAME);
        String password =  request.getParameter(PASSWORD);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new AuthenticationServiceException(FailMsgEnum.username_or_password_not_empty.getMsg());
        }
        MBAuthenticationToken mbAuthenticationToken = new MBAuthenticationToken(username, password);
        mbAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(mbAuthenticationToken);
    }
}