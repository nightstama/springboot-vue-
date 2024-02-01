package com.ns.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.log.Log;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ns.common.Constants;
import com.ns.common.Result;
import com.ns.dao.SysUserDao;
import com.ns.entity.SysUser;
import com.ns.entity.dto.SysUserDto;
import com.ns.exception.BusinessException;
import com.ns.service.SysUserService;
import com.ns.utils.UploadUtil;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
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
    @Resource
    private UploadUtil uploadUtil;
    @Value("${upload.path}")
    private String path;
    private static final Log LOG = Log.get();

    @Override
    public IPage<SysUser> search(@RequestBody SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.like(user.getUsername() != null, "username", user.getUsername());
        wrapper.like(user.getPhone() != null, "phone", user.getPhone());
        wrapper.like(user.getAddress() != null, "address", user.getAddress());
        return sysUserDao.selectPage(new Page<>(user.getPageNum(), user.getPageSize()), wrapper);
    }

    @Override
    public Result export(SysUser user) {
        Result result = new Result(null);
        try {
            List<SysUser> list = selectExport(user);
            String date = DateUtil.formatDate(new Date());
            String fileName = "管理员信息表—" + date + ".xlsx";
            String filePath = path + fileName;
            filePath = filePath.replace("\\\\", "\\");
            filePath = filePath.replace('\"', ' ').trim();
            File file = new File(filePath);

            ExcelWriter excelWriter = EasyExcel.write(file).build();
            WriteSheet writeSheet = EasyExcel.writerSheet().head(SysUserDto.class).build();
            excelWriter.write(list, writeSheet);
            excelWriter.finish();

            String url = uploadUtil.uploadExcel(fileName);
            return Result.success(url);
        } catch (Exception e) {
            Result.success(false);
        }

        return result;
    }

    @Override
    public SysUserDto login(SysUserDto userDto) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userDto.getUsername());
        wrapper.eq("password", userDto.getPassword());
        try {
            SysUser one = getOne(wrapper);
            if (one != null) {
                BeanUtil.copyProperties(one, userDto, true);
                return userDto;
            } else {
                throw new BusinessException(Constants.CODE_600, "用户名密码错误");
            }
        } catch (Exception e) {
            LOG.error(e);
            throw new BusinessException(Constants.CODE_500, "系统错误");
        }
    }

    public List<SysUser> selectExport(SysUser user) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.like(user.getUsername() != null, "username", user.getUsername());
        wrapper.like(user.getPhone() != null, "phone", user.getPhone());
        wrapper.like(user.getAddress() != null, "address", user.getAddress());
        return sysUserDao.selectList(wrapper);
    }
}

