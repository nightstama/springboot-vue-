package com.ns.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ns.dao.SysUserDao;
import com.ns.entity.SysUser;
import com.ns.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 10:37:39
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;
    @Override
    public IPage<SysUser> search(@RequestBody SysUser user) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.like(user.getUsername()!=null,"username",user.getUsername());
        wrapper.like(user.getPhone()!=null,"phone",user.getPhone());
        wrapper.like(user.getAddress()!=null,"address",user.getAddress());
        Page<SysUser> sysUserPage = sysUserDao.selectPage(new Page<>(user.getPageNum(),user.getPageSize()), wrapper);
        return sysUserPage;
    }
}

