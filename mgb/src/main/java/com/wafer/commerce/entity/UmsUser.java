package com.wafer.commerce.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("ums_user")
public class UmsUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -5691748823975387273L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String email;

    private String password;

    @Schema(title = "头像")
    private String icon;

    @Schema(title = "昵称")
    private String nickname;

    @Schema(title = "备注信息")
    private String note;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "最后登录时间")
    private Date loginTime;

    @Schema(title = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

}
