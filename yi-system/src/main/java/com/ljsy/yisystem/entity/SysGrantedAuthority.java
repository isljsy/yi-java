package com.ljsy.yisystem.entity;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.core.GrantedAuthority;

public class SysGrantedAuthority implements GrantedAuthority {

    private final String role;

    @Override
    public String getAuthority() {
        return role;
    }

    public SysGrantedAuthority(String role) {
        Assert.hasText(role, "角色须有一个角色名！");
        this.role = role;
    }

    @Override
    public String toString() {
        return "SysGrantedAuthority{" +
                "role='" + role + '\'' +
                '}';
    }
}
