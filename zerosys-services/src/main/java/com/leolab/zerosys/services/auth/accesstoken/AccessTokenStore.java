package com.leolab.zerosys.services.auth.accesstoken;

import org.springframework.security.core.Authentication;

public interface AccessTokenStore {

    void storeAccessToken(AccessToken token, Authentication authentication);

    Authentication readAuthentication(String token);

    void removeAccessToken(String token);
}
