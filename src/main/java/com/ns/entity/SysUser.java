package com.ns.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * (SysUser)表实体类
 *
 * @author makejava
 * @since 2022-12-15 10:37:38
 */
@Data
@TableName(value = "sys_user",resultMap = "BaseResultMap")
public class SysUser extends Model<SysUser>{
    //id
    private String id;
    //用户名
    private String username;
    //密码
    @JsonIgnore
    private String password;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //电话
    private String phone;
    //地址
    private String address;
    //创建时间
    private Date createTime;
    //头像
    private String avatarUrl;
    //角色
    private String role;
    @TableField(exist =false)
    private int pageNum;
    @TableField(exist =false)
    private int pageSize;
}



