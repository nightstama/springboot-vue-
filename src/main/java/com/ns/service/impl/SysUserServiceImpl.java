package com.ns.service.impl;

import cn.hutool.crypto.symmetric.AES;
import cn.hutool.log.Log;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ns.mapper.SysUserMapper;
import com.ns.entity.SysUser;

import com.ns.entity.dto.SysUserDto;
import com.ns.service.SysUserService;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;


/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2022-12-15 10:37:39
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserDao;
    @Value("${upload.path}")
    private String path;
    private static final Log LOG=Log.get();
    @Override
    public IPage<SysUser> search(@RequestBody SysUser user) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.like(user.getUsername()!=null,"username",user.getUsername());
        wrapper.like(user.getPhone()!=null,"phone",user.getPhone());
        wrapper.like(user.getAddress()!=null,"address",user.getAddress());
        Page<SysUser> sysUserPage = baseMapper.selectPage(new Page<>(user.getPageNum(),user.getPageSize()), wrapper);
        return sysUserPage;
    }

    @Override
    public boolean export(SysUser user) {
        List<SysUser> list=selectExport(user);
        String fileName="管理员信息表—"+System.currentTimeMillis()+".xlsx";
        String filePath=path+fileName;
        filePath = filePath.replace("\\\\", "\\");
        filePath = filePath.replace('\"',' ').trim();
        File file = new File(filePath);

        ExcelWriter excelWriter = EasyExcel.write(file).build();



        WriteSheet writeSheet = EasyExcel.writerSheet().head(SysUserDto.class).build();
        excelWriter.write(list,writeSheet);
        excelWriter.finish();
        return true;
    }

    public List<SysUser> selectExport(SysUser user){
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.like(user.getUsername()!=null,"username",user.getUsername());
        wrapper.like(user.getPhone()!=null,"phone",user.getPhone());
        wrapper.like(user.getAddress()!=null,"address",user.getAddress());
        return sysUserDao.selectList(wrapper);
    }
}

