package com.ns.entity.auth;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Long roleId;

    private String roleName;

    private String roleMean;

    private String roleDesc;

    private Date createTime;

    private Date updateTime;

    private Integer roleActivate;
}
