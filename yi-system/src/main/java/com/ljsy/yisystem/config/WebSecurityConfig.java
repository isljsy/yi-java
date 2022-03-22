package com.ljsy.yisystem.config;

import com.ljsy.yisystem.filter.JsonUsernamePasswordFilter;
import com.ljsy.yisystem.filter.JwtTokenAuthenticationFilter;
import com.ljsy.yisystem.util.JwtTokenProvider;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    private JwtTokenProvider jwtTokenProvider;

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
                //关闭httpBasic，采用自定义过滤器
                .httpBasic().disable()
                //前后端分离架构不需要csrf保护，这里关闭
                .csrf().disable()
                //前后端分离是无状态的，不用session了，直接禁用。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/swagger**/**").permitAll()
//                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
//                .antMatchers("/v2/api-docs/**").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .addFilterAt(jsonAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtTokenAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

    }

    /**
     *
     * @return 登录验证用的自定义过滤器
     */
    @SneakyThrows
    private JsonUsernamePasswordFilter jsonAuthenticationFilter(){
        JsonUsernamePasswordFilter filter = new JsonUsernamePasswordFilter();
        filter.setJwtTokenProvider(jwtTokenProvider);
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl("/auth/login");
        return filter;
    }


    private JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter(){
        return new JwtTokenAuthenticationFilter(jwtTokenProvider);
    }
}
