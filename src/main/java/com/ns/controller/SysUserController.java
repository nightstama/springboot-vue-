package com.ns.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ns.entity.SysUser;
import com.ns.service.SysUserService;
import com.ns.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SysUser)表控制层
 *
 * @author makejava
 * @since 2022-12-15 10:37:36
 */
@RestController
@RequestMapping("/sysUser")
@Api(tags = "管理员管理")
public class SysUserController{
    @Resource
    public SysUserService sysUserService;

    @ApiOperation(value = "管理员查询",notes = "查询管理员")
    @PostMapping("/page")
    public Map<String,Object> search(@RequestBody SysUser user){
        IPage<SysUser> page = sysUserService.search(user);
        List<SysUser> data = page.getRecords();
        Long total = page.getTotal();
        Long pages = page.getPages();
        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("pages", pages);
        res.put("data", data);
        return res;
    }
    @PostMapping("/save")
    public boolean save(@RequestBody SysUser user){
        if (user.getId()==null){
            return sysUserService.save(user);
        }else {
            return sysUserService.updateById(user);
        }
    }

    @PostMapping("/update")
    public boolean update(@RequestBody SysUser user){
        return sysUserService.updateById(user);
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id){
        return sysUserService.removeById(id);
    }
    @PostMapping("/deleteBatch")
    public boolean deleteBatch(@RequestBody List<Long> ids){
        return sysUserService.removeBatchByIds(ids);
    }

    @PostMapping("/export")
    public void export(SysUser user){
        sysUserService.export(user);
    }
}

