//package com.ns.service.impl;
//
//import cn.hutool.crypto.symmetric.AES;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.ns.config.PhonedVerifyAuthenticationToken;
//import com.ns.entity.ResultCode;
//import com.ns.entity.ResultJson;
//import com.ns.entity.SysUser;
//import com.ns.entity.auth.ResponseUserToken;
//import com.ns.entity.auth.Role;
//import com.ns.entity.auth.UserDetail;
//import com.ns.exception.CustomException;
//import com.ns.mapper.AuthMapper;
//import com.ns.service.AuthService;
//import com.ns.util.IdGenerator;
//import com.ns.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.DisabledException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//    private final AuthenticationManager authenticationManager;
//    private final UserDetailsService userDetailsService;
//    private final JwtUtils jwtTokenUtil;
//    private final AuthMapper authMapper;
//
//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;
//
//
//    @Value("${jwt.tokenHead}")
//    private String tokenHead;
//
//    @Autowired
//    public AuthServiceImpl(AuthenticationManager authenticationManager, @Qualifier("CustomUserDetailsService") UserDetailsService userDetailsService, JwtUtils jwtTokenUtil, AuthMapper authMapper) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//        this.authMapper = authMapper;
//    }
//
//    @Override
//    public UserDetail register(UserDetail userDetail) {
//        final String username = userDetail.getUsername();
//        if (authMapper.findByUsername(username) != null) {
//            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST,"用户已存在"));
//        }
//        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String salt = SysUser.getRandom4();
//        final String rawPassword = userDetail.getPassword();
//        userDetail.setUserid(new IdGenerator().snowflakeId());
//        userDetail.setPassword(SysUser.createPassword(rawPassword,salt));
//        userDetail.setLastPasswordResetDate(new Date());
//        userDetail.setSalt(salt);
//        authMapper.insert(userDetail);
//        long roleId = userDetail.getRole().getRoleId();
//        Role role = authMapper.findRoleById(roleId);
//        userDetail.setRole(role);
//        authMapper.UpdateRoleByUidAndRid(userDetail.getUserid(), roleId);
//        return userDetail;
//    }
//
//    @Override
//    public ResponseUserToken login(SysUser user, String password) {
//        //用户验证
//        final Authentication authentication = authenticate(user);
//        //存储认证信息
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        //生成token
//        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
//        final String token = jwtTokenUtil.generateAccessToken(userDetail);
//        //存储token
//        jwtTokenUtil.putToken(((UserDetail) authentication.getPrincipal()).getUsername(), token);
//        return new ResponseUserToken(token, userDetail);
//    }
//    private Authentication authenticate(SysUser user) {
//        AES aes = new AES("ECB", "PKCS7Padding", "tjsd2022ITC66666".getBytes());
//        String key = "";
//        try {
//            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
//            if (StringUtils.isNotBlank(user.getPhone())) {
//                key=user.getPhone();
//                this.isMore(key);
//                return authenticationManager.authenticate(new PhonedVerifyAuthenticationToken(user.getPhone(), user.getVerify_code()));
//            } else {
//                throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, ResultCode.LOGIN_ERROR.getMsg()));
//            }
//        } catch (DisabledException | BadCredentialsException e) {
//            if (stringRedisTemplate.hasKey(key)){
//                Integer num = Integer.valueOf(stringRedisTemplate.opsForValue().get(key));
//                stringRedisTemplate.opsForValue().set(key, String.valueOf(num+1),900, TimeUnit.SECONDS);
//            }else {
//                stringRedisTemplate.opsForValue().set(key, "0",900, TimeUnit.SECONDS);
//            }
//            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
//        }
//    }
//    //判断错误次数过多
//    private void isMore(String key){
//        try {
//            if (stringRedisTemplate.hasKey(key)){
//                Integer num = Integer.valueOf(stringRedisTemplate.opsForValue().get(key));
//                if (5<num){
//                    throw new CustomException(ResultJson.failure(ResultCode.LOGIN_MORE, ResultCode.LOGIN_MORE.getMsg() + num));
//                }
//            }
//        }catch (NullPointerException e){
//
//        }
//
//    }
//
//    @Override
//    public SysUser getByUsername(String username) {
//        return null;
//    }
//
//    @Override
//    public void logout(String token) {
//
//    }
//
//    @Override
//    public ResponseUserToken refresh(String oldToken) {
//        return null;
//    }
//
//    @Override
//    public UserDetail getUserByToken(String token) {
//        return null;
//    }
//
//    @Override
//    public List<Role> findAllRole(String role_mean, Integer role_activate) {
//        return null;
//    }
//
//    @Override
//    public void updateRole(Role role) {
//
//    }
//
//    @Override
//    public void deleteRole(String roleId) {
//
//    }
//
//    @Override
//    public void add(Role role) {
//
//    }
//
//    @Override
//    public Boolean updatePwd(String username, String pwd, String newPwd) {
//        return null;
//    }
//}
