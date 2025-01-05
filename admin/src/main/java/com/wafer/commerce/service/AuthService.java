package com.wafer.commerce.service;

import com.wafer.commerce.common.api.CommonResult;
import com.wafer.commerce.dto.LoginRequestDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;




public interface AuthService {

    CommonResult register(LoginRequestDTO param);


    CommonResult login(LoginRequestDTO param);

    CommonResult authenticate(LoginRequestDTO param);

    public UserDetails loadUserByUsername(String username);

    CommonResult wxLogin();

    CommonResult wxLoginCallback();
}
