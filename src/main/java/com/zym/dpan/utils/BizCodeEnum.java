package com.zym.dpan.utils;

/**
 * 错误码和错误信息定义类
 * 1. 错误码定义规则为 5 为数字
 * 2. 前两位表示业务场景，最后三位表示错误码。例如：100001。10:通用001:系统未知异常
 * 3. 维护错误码后需要维护错误描述，将他们定义为枚举形式
 * 错误码列表：
 * 10: 通用
 * 001：参数格式校验
 *
 * @Author zym
 * @Create 2024/4/7 18:07
 * @Version 1.0
 */
public enum  BizCodeEnum {
    //未知异常
    UNKNOW_EXCEPTION(10000,"系统未知异常"),
    //校验异常
    VALID_EXCEPTION(10001,"参数格式校验出错");

    private int code;

    private String msg;

    BizCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
