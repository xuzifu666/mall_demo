package com.self.mall.common;

public enum ResponseCode {

    SUCCESS_RESPONSE(0,"success"),
    ERROR_RESPONSE(1,"error"),
    NEED_LOGIN(2,"need login"),
    AGRS_ERROR(3,"args error");


    private int code;
    private String desc;

    ResponseCode(int code,String desc){
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
