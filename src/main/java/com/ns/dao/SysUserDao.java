package com.ns.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ns.entity.SysUser;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-15 10:37:37
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUser> {
    List<SysUser> findAll();
}

