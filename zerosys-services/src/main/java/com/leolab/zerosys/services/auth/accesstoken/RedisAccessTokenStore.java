package com.leolab.zerosys.services.auth.accesstoken;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.Authentication;

import static org.springframework.data.redis.connection.RedisStringCommands.SetOption.UPSERT;

public class RedisAccessTokenStore implements AccessTokenStore {
    private static final byte[] EMPTY_ARRAY = new byte[0];

    private static final StringRedisSerializer STRING_SERIALIZER = new StringRedisSerializer();
    private static final JdkSerializationRedisSerializer OBJECT_SERIALIZER = new JdkSerializationRedisSerializer();

    private static final String ACCESS = "access:";
    private static final String AUTH = "auth:";

    private final RedisConnectionFactory connectionFactory;

    public RedisAccessTokenStore(RedisConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void storeAccessToken(AccessToken token, Authentication authentication) {
        if (token.getExpiresIn() > 0) {
            byte[] serialAuthenticationKey = serializeKey(AUTH + token.getValue());
            byte[] serialAuthentication = serialize(authentication);
            RedisConnection conn = getConnection();
            try {
                conn.set(serialAuthenticationKey, serialAuthentication, Expiration.seconds(token.getExpiresIn()), UPSERT);
            } catch (Exception e) {
                conn.close();
            }
        }
    }

    @Override
    public Authentication readAuthentication(String token) {
        if (token == null) {
            return null;
        }
        byte[] bytes = null;
        RedisConnection conn = getConnection();
        try {
            bytes = conn.get(serializeKey(AUTH + token));
        } catch (Exception e) {
            conn.close();
        }
        Authentication authentication = deserializeAuthentication(bytes);
        return authentication;
    }

    @Override
    public void removeAccessToken(String token) {

    }

    private byte[] serializeKey(String key) {
        if (key == null) {
            return EMPTY_ARRAY;
        }
        return STRING_SERIALIZER.serialize(key);
    }

    private byte[] serialize(Object object) {
        return OBJECT_SERIALIZER.serialize(object);
    }

    private Authentication deserializeAuthentication(byte[] bytes) {
        return (Authentication) OBJECT_SERIALIZER.deserialize(bytes);
    }

    private RedisConnection getConnection() {
        return connectionFactory.getConnection();
    }
}
