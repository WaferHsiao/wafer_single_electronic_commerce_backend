package com.wafer.commerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @Schema(title = "邮箱")
    private String email;

    private String password;

    @Schema(title = "头像")
    private String icon;

    @Schema(title = "昵称")
    private String nickName;

}
