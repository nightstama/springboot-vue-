package com.ns.controller;



import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.common.ResultData;
import com.ns.entity.SysUser;

import com.ns.service.SysUserService;
import com.ns.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2022-12-15 10:37:36
 */
@RestController
@RequestMapping("/user")
@Api(tags = "管理员管理")
public class SysUserController{
    @Resource
    public SysUserService sysUserService;
    @Resource
    public UploadService uploadService;

    /**
     * 查询用户
     * @param user
     * @return
     */
    @ApiOperation(value = "管理员查询",notes = "查询管理员")
    @PostMapping("/page")
    public ResultData<IPage<SysUser>> search(@RequestBody SysUser user){
        System.out.println("jj");

        return new ResultData<>(sysUserService.search(user));
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/save")
    public boolean save(@RequestBody SysUser user){
        if (user.getId()==null){
            return sysUserService.save(user);
        }else {
            return sysUserService.updateById(user);
        }
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("/update")
    public boolean update(@RequestBody SysUser user){
        return sysUserService.updateById(user);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return sysUserService.removeById(id);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteBatch")
    public boolean deleteBatch(@RequestBody List<Long> ids){
        return sysUserService.removeBatchByIds(ids);
    }

    /**
     * 导出
     * @param user
     * @return
     */
    @PostMapping("/export")
    public boolean export(SysUser user){
        return sysUserService.export(user);
    }
    /**
     * 上传文件
     */
    @PostMapping("/File/upload")
    public Map<String,Object> upload(){
        return uploadService.upload();
    }


}

