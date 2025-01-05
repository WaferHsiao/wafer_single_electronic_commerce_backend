package com.wafer.commerce.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@TableName("ums_resource")
public class UmsResource implements Serializable {

    @Serial
    private static final long serialVersionUID = -7843714733116972959L;

    private Long id;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "资源名称")
    private String name;

    @Schema(title = "资源URL")
    private String url;

    @Schema(title = "描述")
    private String description;

    @Schema(title = "资源分类ID")
    private Long categoryId;

}
