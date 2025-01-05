package com.wafer.commerce.config;

import com.wafer.commerce.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AdminSecurityConfig {

    @Autowired
    private AuthService authService;


    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> authService.loadUserByUsername(username);
    }

}
