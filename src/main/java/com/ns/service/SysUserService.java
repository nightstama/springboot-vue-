package com.ns.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ns.common.Result;
import com.ns.entity.SysUser;
import com.ns.entity.dto.SysUserDto;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 10:37:39
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> search(SysUser user);

    Result export(SysUser user);

    SysUserDto login(SysUserDto userDto);
}

