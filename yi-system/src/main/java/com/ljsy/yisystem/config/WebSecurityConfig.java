package com.ljsy.yisystem.config;

import com.ljsy.yisystem.filter.RestAuthenticationFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService detailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(detailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .formLogin().disable()
                .httpBasic().disable()
                // 禁用 CSRF
                .csrf().disable()
                .logout().disable()
                .authorizeRequests()
                .antMatchers("/download/db").hasAnyRole("ADMIN")
                .antMatchers("/yi-words/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterAt(restAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    /**
     *
     * @return 登录验证用的自定义过滤器
     */
    @SneakyThrows
    private RestAuthenticationFilter restAuthenticationFilter(){
        RestAuthenticationFilter filter = new RestAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());

        filter.setFilterProcessesUrl("/login");
        return filter;
    }

}
