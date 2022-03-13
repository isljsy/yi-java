package com.ljsy.yisystem.config;

import com.ljsy.yisystem.filter.JwtTokenAuthenticationFilter;
import com.ljsy.yisystem.util.JwtAuthenticationEntryPoint;
import com.ljsy.yisystem.util.JwtTokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private JwtTokenProvider provider;

    public JwtSecurityConfigurer(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtTokenAuthenticationFilter tokenFilter = new JwtTokenAuthenticationFilter(provider);
        builder.exceptionHandling()
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                .and()
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
