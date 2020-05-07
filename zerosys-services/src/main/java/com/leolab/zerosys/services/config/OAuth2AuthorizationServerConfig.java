//package com.leolab.zerosys.services.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static com.leolab.zerosys.common.constant.CommonConstant.ZERO_SYS;
//import static com.leolab.zerosys.services.auth.constant.AuthConstant.OAUTH_PREFIX;
//
///**
// * OAuth2认证服务器配置
// */
//@Configuration
//@EnableAuthorizationServer
//public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//
//	@Autowired
//	private RedisConnectionFactory redisConnectionFactory;
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//	}
//
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
//		oauthServer
//			.allowFormAuthenticationForClients()
//			.checkTokenAccess("permitAll()");
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//
//	}
//
//
//	@Bean
//	public TokenStore tokenStore() {
//		RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
//		tokenStore.setPrefix(OAUTH_PREFIX);
//		return tokenStore;
//	}
//
//	@Bean
//	public TokenEnhancer tokenEnhancer() {
//		return (accessToken, authentication) -> {
//			final Map<String, Object> additionalInfo = new HashMap<>(1);
//			additionalInfo.put("license", ZERO_SYS);
//			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
//			return accessToken;
//		};
//	}
//}
