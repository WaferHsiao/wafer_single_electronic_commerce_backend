package com.wafer.commerce.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wafer.commerce.bo.UmsUserDetails;
import com.wafer.commerce.common.api.CommonResult;

import com.wafer.commerce.common.enums.EnableEnum;
import com.wafer.commerce.common.exception.Asserts;
import com.wafer.commerce.dto.LoginRequestDTO;
import com.wafer.commerce.dto.LoginResponseDTO;
import com.wafer.commerce.entity.UmsResource;
import com.wafer.commerce.entity.UmsUser;
import com.wafer.commerce.mapper.UmsUserMapper;
import com.wafer.commerce.security.util.JwtTokenUtil;
import com.wafer.commerce.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${wx.open.app_id}")
    private String appId;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    @Autowired
    UmsUserMapper umsUserMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public CommonResult register(LoginRequestDTO param) {


        log.info("test9");
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getEmail,param.getEmail());
        UmsUser umsUser = umsUserMapper.selectOne(queryWrapper);
        if (null != umsUser){
            Asserts.fail("该邮箱已注册，注册失败！");
        }
        UmsUser insertUser = new UmsUser();
        insertUser.setPassword(param.getPassword());
        insertUser.setEmail(param.getEmail());
        insertUser.setStatus(EnableEnum.ON.getCode());
        umsUserMapper.insert(insertUser);
        return CommonResult.success();
    }

    @Override
    public CommonResult login(LoginRequestDTO param) {
        //验证数据库中是否有这个用户
        LambdaQueryWrapper<UmsUser> queryWrapper = new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getEmail,param.getEmail())
                .eq(UmsUser::getPassword,param.getPassword());
        UmsUser existUmsUser = umsUserMapper.selectOne(queryWrapper);
        if (existUmsUser == null){
            Asserts.fail("用户未注册！");
        }
        String accessToken = jwtTokenUtil.generateToken(loadUserByUsername(param.getEmail()));
        return CommonResult.success(LoginResponseDTO.builder().accessToken(accessToken).build());    }

    @Override
    public CommonResult authenticate(LoginRequestDTO param) {
        return CommonResult.success();
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        //获取用户信息
        UmsUser umsUser = umsUserMapper.selectOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getEmail, email));

        if (umsUser != null) {
//            List<UmsResource> resourceList = getResourceList(admin.getId());
            List<UmsResource> resourceList = null;
            return new UmsUserDetails(umsUser,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public CommonResult wxLogin() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
        //        "&state=%s" +
                "#wechat_redirect";

        String url = String.format(
                baseUrl,
                appId,
                redirectUrl
        //        "kc"
        );

        return CommonResult.success(url);
    }

    @Override
    public CommonResult wxLoginCallback() {
        return null;
    }
}
