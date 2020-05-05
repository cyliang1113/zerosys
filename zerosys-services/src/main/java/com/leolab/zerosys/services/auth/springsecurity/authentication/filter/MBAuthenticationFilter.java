package com.leolab.zerosys.services.auth.springsecurity.authentication.filter;

import com.leolab.zerosys.common.constant.FailMsgEnum;
import com.leolab.zerosys.services.auth.springsecurity.authentication.token.MBAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 管理后台身份认证(登录) Filter
 */
public class MBAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String M_B_AUTHENTICATION_URL = "/mb/authentication"; //管理后台登录url
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    public MBAuthenticationFilter() {
        super(new AntPathRequestMatcher(M_B_AUTHENTICATION_URL, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        logger.info("MBAuthenticationFilter start ...");

        if (!request.getMethod().equals(HttpMethod.POST.name())) {
            throw new InternalAuthenticationServiceException(
                    "Authentication request method not supported: " + request.getMethod());
        }

        String username = request.getParameter(USERNAME);
        String password =  request.getParameter(PASSWORD);

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new InternalAuthenticationServiceException(FailMsgEnum.username_or_password_not_empty.getMsg());
        }
        MBAuthenticationToken mbAuthenticationToken = new MBAuthenticationToken(username, password);
        mbAuthenticationToken.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(mbAuthenticationToken);
    }
}