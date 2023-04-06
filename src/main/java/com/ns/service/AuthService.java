//package com.ns.service;
//
//import com.ns.entity.SysUser;
//import com.ns.entity.auth.ResponseUserToken;
//import com.ns.entity.auth.Role;
//import com.ns.entity.auth.UserDetail;
//
//import java.util.List;
//
//public interface AuthService {
//    /**
//     * 注册用户
//     * @param userDetail
//     * @return
//     */
//    UserDetail register(UserDetail userDetail);
//
//    /**
//     * 登陆
//     * @param user
//     * @param password
//     * @return
//     */
//    ResponseUserToken login(SysUser user, String password);
//
//    SysUser getByUsername(String username);
//
//    /**
//     * 登出
//     * @param token
//     */
//    void logout(String token);
//
//    /**
//     * 刷新Token
//     * @param oldToken
//     * @return
//     */
//    ResponseUserToken refresh(String oldToken);
//
//    /**
//     * 根据Token获取用户信息
//     * @param token
//     * @return
//     */
//    UserDetail getUserByToken(String token);
//
//
//    List<Role> findAllRole(String role_mean, Integer role_activate);
//
//    void updateRole(Role role);
//
//    void deleteRole(String roleId);
//
//    void add(Role role);
//
//    Boolean updatePwd(String username,String pwd, String newPwd);
//}
