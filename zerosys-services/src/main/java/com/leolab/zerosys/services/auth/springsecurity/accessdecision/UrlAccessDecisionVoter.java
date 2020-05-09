package com.leolab.zerosys.services.auth.springsecurity.accessdecision;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Slf4j
public class UrlAccessDecisionVoter implements AccessDecisionVoter {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        log.info("UrlAccessDecisionVoter start ...");
        FilterInvocation fi = (FilterInvocation) object;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("UrlAccessDecisionVoter request url: {}", fi.getRequestUrl());
        log.info("UrlAccessDecisionVoter authorities: {}", authorities);
        for (GrantedAuthority authority : authorities) {
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(authority.getAuthority());
            if (antPathRequestMatcher.matches(fi.getRequest())) {
                return 1; //通过
            }
        }
        return -1; //拒绝
    }

    @Override
    public boolean supports(Class clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
