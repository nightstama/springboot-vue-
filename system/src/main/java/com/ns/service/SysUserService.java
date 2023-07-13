package com.ns.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ns.entity.SysUser;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 10:37:39
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> search(SysUser user);

    boolean export(SysUser user);

}

