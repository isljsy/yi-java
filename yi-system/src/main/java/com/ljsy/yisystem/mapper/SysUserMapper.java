package com.ljsy.yisystem.mapper;

import com.ljsy.yisystem.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljsy.yisystem.entity.SysUserDetails;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ljsy
 * @since 2022-02-27
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUserDetails getUserDetailsByUsername(String username);
}
