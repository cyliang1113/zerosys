package com.leolab.zerosys.services.auth.accesstoken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;
import java.util.UUID;

public class DefaultAccessTokenService implements AccessTokenService {

    public static final int  ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 12; // default 12 hours.

    private Integer accessTokenValiditySeconds;

    @Autowired
    private RedisAccessTokenStore redisAccessTokenStore;

    @Override
    public AccessToken createAccessToken(Authentication authentication) throws AuthenticationException {
        //生成 access token
        AccessToken accessToken = new AccessToken(UUID.randomUUID().toString(),
                new Date(System.currentTimeMillis() + (getAccessTokenValiditySeconds() * 1000L)));
        //保存 access token
        redisAccessTokenStore.storeAccessToken(accessToken, authentication);

        return accessToken;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    protected int getAccessTokenValiditySeconds() {
        if (accessTokenValiditySeconds != null) {
            return accessTokenValiditySeconds;
        }
        return ACCESS_TOKEN_VALIDITY_SECONDS;
    }
}
