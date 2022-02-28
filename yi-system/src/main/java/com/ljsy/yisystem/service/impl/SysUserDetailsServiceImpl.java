package com.ljsy.yisystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ljsy.yisystem.entity.SysGrantedAuthority;
import com.ljsy.yisystem.entity.SysRole;
import com.ljsy.yisystem.entity.SysUserDetails;
import com.ljsy.yisystem.entity.SysUserRole;
import com.ljsy.yisystem.mapper.SysRoleMapper;
import com.ljsy.yisystem.mapper.SysUserMapper;
import com.ljsy.yisystem.mapper.SysUserRoleMapper;
import com.ljsy.yisystem.service.ISysRoleService;
import com.ljsy.yisystem.service.ISysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    /**
     * @param username 用户名
     * @return 用户权限类
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 找到用户
        SysUserDetails userDetails = userMapper.getUserDetailsByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        // 查询用户角色id
        List<SysUserRole> userRoles = userRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id",userDetails.getId()));

        // id查询角色名
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        QueryWrapper<SysRole> roleQueryWrapper = new QueryWrapper<>();
        for(SysUserRole userRole : userRoles){
            SysRole role = roleMapper.selectOne(roleQueryWrapper.eq("id",userRole.getRoleId()));
            authoritySet.add(new SysGrantedAuthority(role.getRole()));
        }

        // 赋值给details对象角色
        userDetails.setAuthorities(authoritySet);
        System.out.println("当前用户"+userDetails);
        return userDetails;
    }
}
