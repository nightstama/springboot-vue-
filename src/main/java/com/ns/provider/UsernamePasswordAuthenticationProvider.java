package com.ns.provider;

import com.ns.entity.auth.UserDetail;
import com.ns.service.impl.CustomUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.ns.entity.SysUser.createPassword;

@Slf4j
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetail user = userDetailsService.loadUserByUsername(username);
        if (null == user) {
            throw new AuthenticationServiceException("Authentication username or password is incorrect");
        }
        // 校验密码
        if (null != user.getSalt()) {
            password = createPassword(password, user.getSalt());
            if (!user.getPassword().equals(password)) {
                throw new AuthenticationServiceException("Authentication username or password is incorrect");
            }
        } else {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                throw new AuthenticationServiceException("Authentication username or password is incorrect");
            }
        }
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
    }

    public String createPassword(String password, String salt) {
        //salt 密码盐，用户添加生成 4位随机数字;密码传输规则md5(明文密码+*v1#)  密码存储规则 md5(md5(明文密码+*v1#)+salt)
        return DigestUtils.md5Hex(DigestUtils.md5Hex(password + "*v1#") + salt);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
