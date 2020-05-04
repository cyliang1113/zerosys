package com.leolab.zerosys.services.config;

import com.leolab.zerosys.services.auth.authentication.filter.MBAuthenticationFilter;
import com.leolab.zerosys.services.auth.authentication.provider.MBAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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
public class AuthConfig extends WebSecurityConfigurerAdapter {

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
        http.apply(new SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {
            @Override
            public void configure(HttpSecurity builder) throws Exception {
                MBAuthenticationFilter mbAuthenticationFilter = getMBAuthenticationFilter();
                mbAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));

                builder.addFilterAfter(mbAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                        .authenticationProvider(getMBAuthenticationProvider());
                authenticationManager();
            }
        });
    }

    @Bean
    public MBAuthenticationFilter getMBAuthenticationFilter() {
        return new MBAuthenticationFilter();
    }

    @Bean
    public MBAuthenticationProvider getMBAuthenticationProvider() {
        return new MBAuthenticationProvider();
    }
}
