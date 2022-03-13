package com.ljsy.yisystem.filter;

import com.ljsy.yisystem.exception.InvalidJwtAuthenticationException;
import com.ljsy.yisystem.util.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenAuthenticationFilter extends GenericFilter {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String token = jwtTokenProvider.resolveToken(request);
            if(token != null && jwtTokenProvider.validateToke(token)){
                Authentication authentication = jwtTokenProvider.getAuthentication(token);

                if(authentication != null){
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (InvalidJwtAuthenticationException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("非法token");
            response.getWriter().flush();
            return;
        }
        filterChain.doFilter(request,response);
    }
}
