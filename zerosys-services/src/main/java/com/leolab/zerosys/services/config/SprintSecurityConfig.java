package com.leolab.zerosys.services.config;

import com.leolab.zerosys.services.auth.accesstoken.AccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.AccessTokenStore;
import com.leolab.zerosys.services.auth.accesstoken.DefaultAccessTokenService;
import com.leolab.zerosys.services.auth.accesstoken.RedisAccessTokenStore;
import com.leolab.zerosys.services.auth.springsecurity.filter.MBAuthenticationFilter;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBAuthenticationFailureHandler;
import com.leolab.zerosys.services.auth.springsecurity.handler.MBAuthenticationSuccessHandler;
import com.leolab.zerosys.services.auth.springsecurity.provider.MBAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * spring security配置
 */
@Configuration
@EnableWebSecurity
public class SprintSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/mb/authentication")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable();

        http.apply(new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>(){
            @Override
            public void configure(HttpSecurity http){
                http.addFilterAfter(createMBAuthenticationFilter(http.getSharedObject(AuthenticationManager.class)),
                        UsernamePasswordAuthenticationFilter.class)
                        .authenticationProvider(mBAuthenticationProvider());
            }
        });
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
    public AccessTokenService accessTokenService() {
        return new DefaultAccessTokenService();
    }

    @Bean
    public AccessTokenStore accessTokenStore(RedisConnectionFactory connectionFactory){
        return new RedisAccessTokenStore(connectionFactory);
    }
}
