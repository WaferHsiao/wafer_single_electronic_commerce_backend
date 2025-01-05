package com.wafer.commerce.controller;

import com.wafer.commerce.common.api.CommonResult;
import com.wafer.commerce.dto.LoginRequestDTO;
import com.wafer.commerce.service.AuthService;
import com.wafer.commerce.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public CommonResult register(@RequestBody LoginRequestDTO param){
        return authService.register(param);
    }

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginRequestDTO param){
        return authService.login(param);

    }

    @PostMapping("/authenticate")
    public CommonResult authenticate(@RequestBody LoginRequestDTO param){
        return authService.authenticate(param);
    };

    @PostMapping("/wxLogin")
    public CommonResult wxLogin(){
        return authService.wxLogin();
    }

    @PostMapping("/callback")
    public CommonResult wxLoginCallback(){
        return authService.wxLoginCallback();
    }


}
