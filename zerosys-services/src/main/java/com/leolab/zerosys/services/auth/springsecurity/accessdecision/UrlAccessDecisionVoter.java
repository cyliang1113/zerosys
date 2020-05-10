package com.leolab.zerosys.services.auth.springsecurity.accessdecision;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Slf4j
public class UrlAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection collection) {
        if (authentication instanceof AnonymousAuthenticationToken) {
            log.info("拒绝匿名访问");
            throw new AccessDeniedException("拒绝匿名访问");
        }
        log.info("UrlAccessDecisionVoter start ...");
        FilterInvocation fi = object;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object principal = authentication.getPrincipal();
        log.info("UrlAccessDecisionVoter request url: {}", fi.getRequestUrl());
        log.info("UrlAccessDecisionVoter username: {},  authorities: {}", principal, authorities);
        for (GrantedAuthority authority : authorities) {
            AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher(authority.getAuthority());
            if (antPathRequestMatcher.matches(fi.getRequest())) {
                return 1; //通过
            }
        }
        log.info("拒绝访问, 没有权限");
        return -1; //拒绝
    }

    @Override
    public boolean supports(Class clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
