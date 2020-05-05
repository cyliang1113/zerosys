package com.leolab.zerosys.services.config;

import com.leolab.zerosys.services.auth.springsecurity.authentication.filter.MBAuthenticationFilter;
import com.leolab.zerosys.services.auth.springsecurity.authentication.handler.MBAuthenticationFailureHandler;
import com.leolab.zerosys.services.auth.springsecurity.authentication.handler.MBAuthenticationSuccessHandler;
import com.leolab.zerosys.services.auth.springsecurity.authentication.provider.MBAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SprintSecurityAuthConfig extends WebSecurityConfigurerAdapter {

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
        mbAuthenticationFilter.setAuthenticationSuccessHandler(new MBAuthenticationSuccessHandler());
        mbAuthenticationFilter.setAuthenticationFailureHandler(new MBAuthenticationFailureHandler());
        return mbAuthenticationFilter;
    }

    @Bean
    public AuthenticationProvider mBAuthenticationProvider() {
        return new MBAuthenticationProvider();
    }
}
