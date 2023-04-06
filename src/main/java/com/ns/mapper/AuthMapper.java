//package com.ns.mapper;
//
//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.ns.entity.SysUser;
//import com.ns.entity.auth.Role;
//import com.ns.entity.auth.UserDetail;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//@Mapper
//public interface AuthMapper extends BaseMapper<SysUser> {
//    /**
//     * 根据用户名查找用户
//     * @param usercode
//     * @return
//     */
//    UserDetail findByUsername(@Param("usercode") String usercode);
//
//    /**
//     * 创建新用户
//     * @param userDetail
//     */
//    void insert(UserDetail userDetail);
//
//    /**
//     * 修改用户角色
//     * @param userId
//     * @param roleId
//     * @return
//     */
//    int UpdateRoleByUidAndRid(@Param("userId") long userId, @Param("roleId") long roleId);
//
//    int updatePwd(String pwd,Long userId);
//
//    /**
//     * 根据角色id查找角色
//     * @param roleId
//     * @return
//     */
//    Role findRoleById(@Param("roleId") long roleId);
//
//    /**
//     * 根据用户id查找该用户角色
//     * @param userId
//     * @return
//     */
//    Role findRoleByUserId(@Param("userId") long userId);
//
//    String findSaltByUserName(@Param("plain_usercode") String plain_usercode);
//
//    SysUser selectByPhone(@Param("phone") String phone);
//
//    List<Role> findAllRole(String role_mean, Integer role_activate);
//
//    void UpdateRole(Role role);
//
//    void deleteRole(Long roleId);
//
//    void insertRole(Role role);
//
//    SysUser findUserByUsername(String username);
//}
