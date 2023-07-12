package com.ns.entity.auth;//package com.ns.entity.auth;
//
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//@Data
//public class UserDetail implements UserDetails {
//    private long userid;
//    private String usercode;
//    private String password;
//    private String salt;
//    private Role role;
//    private Date lastPasswordResetDate;
//    private Boolean userActivate;
//
//    public UserDetail(
//            long userid,
//            String usercode,
//            Role role,
////            Date lastPasswordResetDate,
//            String password) {
//        this.userid = userid;
//        this.usercode = usercode;
//        this.password = password;
//        this.role = role;
////        this.lastPasswordResetDate = lastPasswordResetDate;
//    }
//
//    public UserDetail(String usercode, String password, Role role) {
//        this.usercode = usercode;
//        this.password = password;
//        this.role = role;
//    }
//
//    public UserDetail(long userid, String usercode, String password) {
//        this.userid = userid;
//        this.usercode = usercode;
//        this.password = password;
//    }
//
//    public UserDetail(long userid, String usercode, String password, String salt) {
//        this.userid = userid;
//        this.usercode = usercode;
//        this.password = password;
//        this.salt = salt;
//    }
//    //返回分配给用户的角色列表
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        if(role != null){
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//
//        return authorities;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}
