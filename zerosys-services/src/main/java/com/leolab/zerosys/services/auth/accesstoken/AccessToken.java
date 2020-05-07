package com.leolab.zerosys.services.auth.accesstoken;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 访问令牌
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String BEARER_TYPE = "Bearer";

    @Setter
    @Getter
    private String value;

    @Setter
    @Getter
    private Date expiration;

    @Getter
    private String tokenType = BEARER_TYPE;

    public AccessToken(String value, Date expiration) {
        this.value = value;
        this.expiration = expiration;
    }

    public int getExpiresIn() {
        return expiration != null ? Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L)
                .intValue() : 0;
    }
}
