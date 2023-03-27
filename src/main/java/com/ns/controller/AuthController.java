package com.ns.controller;


import com.ns.common.ResultData;
import com.ns.entity.SysUser;
import com.ns.entity.auth.ResponseUserToken;
import com.ns.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResultData<ResponseUserToken> login(
            @RequestBody SysUser user) {
        final ResponseUserToken response = authService.login(user, user.getPassword());
        return new ResultData<>(response);
    }

    @GetMapping(value = "/logout")
    public ResultData<Boolean> logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return new ResultData<>(false);
        }
        authService.logout(token);
        return new ResultData<>(true);
    }
}
