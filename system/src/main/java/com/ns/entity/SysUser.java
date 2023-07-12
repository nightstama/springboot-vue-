package com.ns.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.Random;

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
    private Long id;
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
    //密码盐
    private String salt;
    private String verify_code;
    @TableField(exist =false)
    private int pageNum;
    @TableField(exist =false)
    private int pageSize;
    public SysUser() {
    }

    public SysUser(Long userid, String username, String salt, String password,  String phone, String email,String verify_code) {
        this.id = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.verify_code=verify_code;
    }

    public static String createPassword(String password, String salt) {
        //salt 密码盐，用户添加生成 4位随机数字;密码传输规则md5(明文密码+*v1#)  密码存储规则 md5(md5(明文密码+*v1#)+salt)
        return DigestUtils.md5Hex(DigestUtils.md5Hex(password + "*v1#") + salt);
    }

    public static String getRandom4() {
        Random random = new Random();
        StringBuilder fourRandom = new StringBuilder(random.nextInt(10000) + "");
        int randLength = fourRandom.length();
        if (randLength < 4) {
            for (int i = 1; i <= 4 - randLength; i++) {
                fourRandom.insert(0, "0");
            }
        }
        return fourRandom.toString();
    }
}



