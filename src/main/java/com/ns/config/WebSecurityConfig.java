//package com.ns.config;
//
//
//import cn.hutool.http.ContentType;
//import com.ns.common.ResultData;
//import com.ns.provider.PhonedVerifyAuthenticationProvider;
//import com.ns.provider.UsernamePasswordAuthenticationProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.annotation.Resource;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private JwtAuthenticationEntryPoint unauthorizedHandler;
//
//    @Autowired
//    private AccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    private UserDetailsService CustomUserDetailsService;
//
//    @Autowired
//    private JwtAuthenticationTokenFilter authenticationTokenFilter;
//
//    @Autowired
//    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
//    @Resource
//    private PhonedVerifyAuthenticationProvider phonedVerifyAuthenticationProvider;
//
//
//    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
//                             @Qualifier("RestAuthenticationAccessDeniedHandler") AccessDeniedHandler accessDeniedHandler,
//                             @Qualifier("CustomUserDetailsService") UserDetailsService CustomUserDetailsService,
//                             JwtAuthenticationTokenFilter authenticationTokenFilter) {
//        this.unauthorizedHandler = unauthorizedHandler;
//        this.accessDeniedHandler = accessDeniedHandler;
//        this.CustomUserDetailsService = CustomUserDetailsService;
//        this.authenticationTokenFilter = authenticationTokenFilter;
//    }
//
//    /**
//     * 装载BCrypt密码编码器
//     *
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
//                // 由于使用的是JWT，我们这里不需要csrf
//                .csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                // 基于token，所以不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
//                    response.setContentType(ContentType.JSON.getValue());
//                    response.setCharacterEncoding("UTF-8");
//                    response.getWriter().println(new ResultData<>(true));
//                }).and()
//                .authorizeRequests()
//                // 对于获取token的rest api要允许匿名访问
//                .antMatchers("/login/**","/updatePwd", "/sign", "/error/**","/vo/**","/tabSysUser/**","/tabRmsClassify/**","/dict/**","/op/**","/Word/**","/Dict/**","/Area/**","/Module/**","/role/**","/user/**","/problem/**","/dealer/**").permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated();
//
//        // 禁用缓存
//        httpSecurity.headers().cacheControl();
//
//
//        // 添加JWT filter
//        httpSecurity
//                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(usernamePasswordAuthenticationProvider)
//                .authenticationProvider(phonedVerifyAuthenticationProvider)
//                //设置UserDetailsService
//                .userDetailsService(this.CustomUserDetailsService)
//                // 使用BCrypt进行密码的hash
//                .passwordEncoder(passwordEncoder());
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//        web
//                .ignoring()
//                .antMatchers(
//                        "/favicon.ico",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/**/*.png"
//                );
//
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
