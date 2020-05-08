package com.leolab.zerosys.services.auth.accesstoken;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import static com.leolab.zerosys.services.auth.constant.AuthConstant.BEARER_TYPE;

/**
 * 访问令牌
 */
public class AccessToken implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public boolean isExpired() {
        return expiration != null && expiration.before(new Date());
    }
}
