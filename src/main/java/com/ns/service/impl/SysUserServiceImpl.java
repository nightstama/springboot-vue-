package com.ns.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ns.dao.SysUserDao;
import com.ns.entity.SysUser;
import com.ns.entity.dto.SysUserDto;
import com.ns.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
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
    @Value("${upload.path}")
    private String path;
    @Override
    public IPage<SysUser> search(@RequestBody SysUser user) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.like(user.getUsername()!=null,"username",user.getUsername());
        wrapper.like(user.getPhone()!=null,"phone",user.getPhone());
        wrapper.like(user.getAddress()!=null,"address",user.getAddress());
        Page<SysUser> sysUserPage = sysUserDao.selectPage(new Page<>(user.getPageNum(),user.getPageSize()), wrapper);
        return sysUserPage;
    }

    @Override
    public void export(SysUser user) {
        List<SysUser> list=selectExport(user);
//        if (list!=null&& !list.isEmpty()){
//            List<SysUserDto> list1=new ArrayList<>();
//            for (SysUser sysUser : list) {
//                SysUserDto sysUserDto=new SysUserDto();
//                BeanUtils.copyProperties(sysUser,sysUserDto);
//                list1.add(sysUserDto);
//            }
//        }



        String fileName="管理员信息表—"+System.currentTimeMillis()+".xlsx";
        String filePath=path+fileName;
        filePath = filePath.replace("\\\\", "\\");
        filePath = filePath.replace('\"',' ').trim();
        File file = new File(filePath);

        ExcelWriter excelWriter = EasyExcel.write(file).build();



        WriteSheet writeSheet = EasyExcel.writerSheet().head(SysUser.class).build();
        excelWriter.write(list,writeSheet);
        excelWriter.finish();
    }
    public List<SysUser> selectExport(SysUser user){
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.like(user.getUsername()!=null,"username",user.getUsername());
        wrapper.like(user.getPhone()!=null,"phone",user.getPhone());
        wrapper.like(user.getAddress()!=null,"address",user.getAddress());
        return sysUserDao.selectList(wrapper);
    }
}

