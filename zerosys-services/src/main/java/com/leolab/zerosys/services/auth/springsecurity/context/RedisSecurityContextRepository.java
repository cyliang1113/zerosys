package com.leolab.zerosys.services.auth.springsecurity.context;

import com.leolab.zerosys.services.auth.accesstoken.AccessTokenStore;
import com.leolab.zerosys.services.auth.accesstoken.RedisAccessTokenStore;
import com.leolab.zerosys.services.auth.util.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedisSecurityContextRepository implements SecurityContextRepository {

    @Autowired
    private RedisAccessTokenStore redisAccessTokenStore;

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        String tokenValue = AuthUtils.extractHttpHeaderBearerAuthorization(request);
        Authentication authentication = null;
        if (tokenValue != null) {
            authentication = redisAccessTokenStore.readAuthentication(tokenValue);
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
        return false;
    }
}
