package com.ns.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@ColumnWidth(25)
public class SysUserDto {
    @ExcelProperty("用户名")
    private String username;
    private String password;
    @ExcelProperty("昵称")
    private String nickname;
    @ExcelProperty("邮箱")
    private String email;
    @ExcelProperty("电话")
    private String phone;
    @ExcelProperty("地址")
    private String address;
    @ExcelProperty("创建时间")
    private Date createTime;
    @ExcelProperty("头像")
    private String avatarUrl;
    @ExcelProperty("角色")
    private String role;
    private String token;

}
