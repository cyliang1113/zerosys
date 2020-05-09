package com.leolab.zerosys.services.config;

import com.leolab.zerosys.services.auth.accesstoken.DefaultAccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.RedisAccessTokenStore;
import com.leolab.zerosys.services.auth.springsecurity.accessdecision.UrlAccessDecisionVoter;
import com.leolab.zerosys.services.auth.springsecurity.context.RedisSecurityContextRepository;
import com.leolab.zerosys.services.auth.springsecurity.filter.MBAuthenticationFilter;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBAuthenticationFailureHandler;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBAuthenticationSuccessHandler;
import com.leolab.zerosys.services.auth.springsecurity.provider.MBAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.Arrays;
import java.util.Collection;


/**
 * spring security配置
 */
@Configuration
@EnableWebSecurity
public class SprintSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .accessDecisionManager(new UnanimousBased(Arrays.asList(new UrlAccessDecisionVoter())))
                .and()
                .csrf()
                .disable();

        http.apply(new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>(){
            @Override
            public void configure(HttpSecurity http){
                http
                        .addFilterAfter(
                                createMBAuthenticationFilter(http.getSharedObject(AuthenticationManager.class)),
                                UsernamePasswordAuthenticationFilter.class
                        )
                        .authenticationProvider(mBAuthenticationProvider());
            }
        });

        http.setSharedObject(SecurityContextRepository.class, redisSecurityContextRepository());
    }

    private MBAuthenticationFilter createMBAuthenticationFilter(AuthenticationManager authenticationManager){
        MBAuthenticationFilter mbAuthenticationFilter = new MBAuthenticationFilter();
        mbAuthenticationFilter.setAuthenticationManager(authenticationManager);
        mbAuthenticationFilter.setAuthenticationSuccessHandler(mBAuthenticationSuccessHandler());
        mbAuthenticationFilter.setAuthenticationFailureHandler(mBAuthenticationFailureHandler());
        return mbAuthenticationFilter;
    }

    @Bean
    public AuthenticationProvider mBAuthenticationProvider() {
        return new MBAuthenticationProvider();
    }

    @Bean
    public MBAuthenticationSuccessHandler mBAuthenticationSuccessHandler() {
        return new MBAuthenticationSuccessHandler();
    }

    @Bean
    public MBAuthenticationFailureHandler mBAuthenticationFailureHandler() {
        return new MBAuthenticationFailureHandler();
    }

    @Bean
    public DefaultAccessTokenService defaultAccessTokenService() {
        return new DefaultAccessTokenService();
    }

    @Bean
    public RedisAccessTokenStore redisAccessTokenStore(RedisConnectionFactory connectionFactory){
        return new RedisAccessTokenStore(connectionFactory);
    }

    @Bean
    public RedisSecurityContextRepository redisSecurityContextRepository() {
        return new RedisSecurityContextRepository();
    }
}
