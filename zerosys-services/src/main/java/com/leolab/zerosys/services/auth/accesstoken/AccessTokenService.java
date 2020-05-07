package com.leolab.zerosys.services.auth.accesstoken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public interface AccessTokenService {
    AccessToken createAccessToken(Authentication authentication) throws AuthenticationException;
}
