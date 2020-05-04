package com.leolab.zerosys.services.auth.authenticationtoken;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MBAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public MBAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MBAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
