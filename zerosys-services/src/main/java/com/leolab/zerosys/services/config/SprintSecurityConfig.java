package com.leolab.zerosys.services.config;

import com.leolab.zerosys.services.auth.accesstoken.DefaultAccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.RedisAccessTokenStore;
import com.leolab.zerosys.services.auth.springsecurity.accessdecision.UrlAccessDecisionVoter;
import com.leolab.zerosys.services.auth.springsecurity.context.RedisSecurityContextRepository;
import com.leolab.zerosys.services.auth.springsecurity.filter.MBLoginFilter;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBLoginFailureHandler;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBLoginSuccessHandler;
import com.leolab.zerosys.services.auth.springsecurity.provider.MBLoginProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.Arrays;


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

    private MBLoginFilter createMBAuthenticationFilter(AuthenticationManager authenticationManager){
        MBLoginFilter mbLoginFilter = new MBLoginFilter();
        mbLoginFilter.setAuthenticationManager(authenticationManager);
        mbLoginFilter.setAuthenticationSuccessHandler(mBAuthenticationSuccessHandler());
        mbLoginFilter.setAuthenticationFailureHandler(mBAuthenticationFailureHandler());
        return mbLoginFilter;
    }

    @Bean
    public AuthenticationProvider mBAuthenticationProvider() {
        return new MBLoginProvider();
    }

    @Bean
    public MBLoginSuccessHandler mBAuthenticationSuccessHandler() {
        return new MBLoginSuccessHandler();
    }

    @Bean
    public MBLoginFailureHandler mBAuthenticationFailureHandler() {
        return new MBLoginFailureHandler();
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
