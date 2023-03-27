package com.ns.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ns.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-15 10:37:37
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<SysUser> findAll();

    String getRoleMean(String id);

    String getRoleId(String mean);

    List<String> getAllRoleMean();

    List<SysUser> getAll();

    Long getUserIdByPhone(String phone);

    String getUserActivate(String userid);
}

