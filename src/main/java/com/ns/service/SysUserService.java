package com.ns.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ns.entity.SysUser;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2022-12-15 10:37:39
 */
public interface SysUserService extends IService<SysUser> {
    IPage<SysUser> search(@RequestBody SysUser user);

    void export(SysUser user);
}

