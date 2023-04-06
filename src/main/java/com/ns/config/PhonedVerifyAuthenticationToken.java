//package com.ns.config;
//
//import lombok.Getter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.util.Assert;
//
//import java.util.Collection;
//@Getter
//public class PhonedVerifyAuthenticationToken extends AbstractAuthenticationToken {
//    private final Object principal;
//    private String credentials;
//
//    public PhonedVerifyAuthenticationToken(String principal, String credentials) {
//        super(null);
//        this.principal = principal;
//        this.credentials = credentials;
//    }
//
//    public PhonedVerifyAuthenticationToken(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
//        super(authorities);
//        this.principal = principal;
//        this.credentials = credentials;
//    }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
//        super.setAuthenticated(false);
//    }
//
//    @Override
//    public void eraseCredentials() {
//        super.eraseCredentials();
//        this.credentials = null;
//    }
//}
