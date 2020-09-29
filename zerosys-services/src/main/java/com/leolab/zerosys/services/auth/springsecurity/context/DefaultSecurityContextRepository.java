package com.leolab.zerosys.services.auth.springsecurity.context;

import com.leolab.zerosys.services.auth.accesstoken.AccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.DefaultAccessTokenService;
import com.leolab.zerosys.services.auth.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SecurityContextPersistenceFilter调用loadContext
 * 通过token获取用户权限
 */
public class DefaultSecurityContextRepository implements SecurityContextRepository {
    @Autowired
    private AccessTokenService accessTokenService;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        String token = AuthUtils.extractHttpHeaderBearerAuthorization(request);
        Authentication authentication = null;
        if (token != null) {
            authentication = accessTokenService.loadAuthentication(token);
        }
        SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
        if (authentication != null) {
            emptyContext.setAuthentication(authentication);
        }
        return emptyContext;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return true;
    }
}
