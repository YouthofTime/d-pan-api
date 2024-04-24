package com.zym.dpan.utils;





import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.http.HttpStatus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * ClassName: R
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/7 16:44
 * @Version 1.0
 */
public class R<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private int status;
    private String message;
    private T data;

    private R(int status) {
        this.status = status;
    }

    private R(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private R(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private R(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> R<T> success(){
        return new R<T>(Constant.ResponseCode.SUCCESS.getCode(),Constant.ResponseCode.SUCCESS.getDesc());
    }

    public static <T> R<T> success(String message){
        return new R<T>(Constant.ResponseCode.SUCCESS.getCode(),message);
    }

    public static <T> R<T> success(String message,T data){
        return new R<T>(Constant.ResponseCode.SUCCESS.getCode(),message,data);
    }
    public static <T> R<T> data(T data){
        return new R<T>(Constant.ResponseCode.SUCCESS.getCode(),Constant.ResponseCode.SUCCESS.getDesc(),data);
    }

    public static <T> R<T> fail() {
        return new R<T>(Constant.ResponseCode.ERROR.getCode(), Constant.ResponseCode.ERROR.getDesc());
    }

    public static <T> R<T> fail(String message) {
        return new R<T>(Constant.ResponseCode.ERROR.getCode(), message);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<T>(code, message);
    }

    public static <T> R<T> fail(int code, String message,T data) {
        return new R<T>(code, message,data);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }






}
