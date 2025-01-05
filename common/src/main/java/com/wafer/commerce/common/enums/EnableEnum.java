package com.wafer.commerce.common.enums;

public enum EnableEnum {
    ON(1,"启用"),
    OFF(0,"禁用");

    int code;
    String desc;


    EnableEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
