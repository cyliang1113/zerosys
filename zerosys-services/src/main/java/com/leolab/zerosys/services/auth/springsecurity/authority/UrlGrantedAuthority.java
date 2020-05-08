package com.leolab.zerosys.services.auth.springsecurity.authority;

import org.springframework.security.core.GrantedAuthority;

public class UrlGrantedAuthority  implements GrantedAuthority {
    private static final long serialVersionUID = 1L;

    private final String url;

    public UrlGrantedAuthority(String url) {
        this.url = url;
    }

    @Override
    public String getAuthority() {
        return url;
    }

    @Override
    public String toString() {
        return url;
    }
}
