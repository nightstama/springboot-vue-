package com.ns.provider;//package com.ns.provider;
//
//import com.ns.config.PhonedVerifyAuthenticationToken;
//import com.ns.entity.SysUser;
//import com.ns.entity.auth.Role;
//import com.ns.entity.auth.UserDetail;
//import com.ns.mapper.AuthMapper;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class PhonedVerifyAuthenticationProvider implements AuthenticationProvider {
//    @Resource
//    private AuthMapper authMapper;
//
//    @Resource
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String phone = authentication.getName();
//        String verifyCode = (String) authentication.getCredentials();
//        SysUser user = authMapper.selectByPhone(phone);
//        if (null == user) {
//            throw new AuthenticationServiceException("Authentication phone or verify is incorrect");
//        }
//        // TODO: 验证码校验
//        String key = "verify:code:" + phone;
//        if (Boolean.FALSE.equals(redisTemplate.hasKey(key))) {
//            throw new AuthenticationServiceException("Authentication phone or verify is incorrect");
//        }
//        String verify = redisTemplate.opsForValue().get(key);
//        if (!verifyCode.equals(verify)) {
//            throw new AuthenticationServiceException("Authentication phone or verify is incorrect");
//        }
//        UserDetail userDetail = new UserDetail(user.getId(), user.getUsername(), user.getPassword(), user.getSalt());
//        Role role = authMapper.findRoleByUserId(userDetail.getUserid());
//        userDetail.setRole(role);
//        return new UsernamePasswordAuthenticationToken(userDetail, userDetail.getPassword(), userDetail.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return PhonedVerifyAuthenticationToken.class.isAssignableFrom(aClass);
//    }
//}
