package com.self.mall.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseObj<T> implements Serializable {


    private int code;

    private String msg;

    private T data;

    public ResponseObj(int code){
        this.code = code;
    }

    public ResponseObj(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResponseObj(int code,T data){
        this.code = code;
        this.data = data;
    }

    public ResponseObj(int code, String msg,T data){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public static <T> ResponseObj createSuccess(T data){
        return new ResponseObj<>(ResponseCode.SUCCESS_RESPONSE.getCode(),data);
    }

    public static ResponseObj createSuccess(String msg){
        return new ResponseObj(ResponseCode.SUCCESS_RESPONSE.getCode(),msg);
    }

    public static <T> ResponseObj createSuccess(String msg,T data){
        return new ResponseObj(ResponseCode.SUCCESS_RESPONSE.getCode(),msg,data);
    }

    public static ResponseObj createSuccess(){
        return new ResponseObj(ResponseCode.SUCCESS_RESPONSE.getCode(),ResponseCode.SUCCESS_RESPONSE.getDesc());
    }

    public static ResponseObj createError(String msg){
        return new ResponseObj(ResponseCode.ERROR_RESPONSE.getCode(),msg);
    }

    public static ResponseObj createError(){
        return new ResponseObj(ResponseCode.ERROR_RESPONSE.getCode(),ResponseCode.ERROR_RESPONSE.getDesc());
    }


    public static ResponseObj createError(int code,String msg){
        return new ResponseObj(code,msg);
    }

    @JsonIgnore
    public boolean isSuccess(){
        return this.code == 0;
    }

}
