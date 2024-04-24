package com.zym.dpan.utils;

/**
 * ClassName: Constant
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/10 15:15
 * @Version 1.0
 */
public class Constant {
    public static final Integer CHUNK_EXPIRATION_DAYS = 1;
    /**
     * 分块上传合并标志
     */
    public enum ChunkMergeFlagEnum{
        // 合并与否
        READY(1,"可以合并"),NOT_READY(0,"未准备好，不合并");
        private Integer code;
        private String msg;
        ChunkMergeFlagEnum(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }
    /**
     * 公用返回码枚举类
     * Created by RubinChu on 2021/1/22 下午 4:11
     */
    public enum ResponseCode {
        /**
         * 成功
         */
        SUCCESS(0, "SUCCESS"),
        /**
         * 错误
         */
        ERROR(1, "ERROR"),
        /**
         * 需要登录
         */
        NEED_LOGIN(10, "NEED_LOGIN"),
        /**
         * token过期
         */
        TOKEN_EXPIRE(2, "TOKEN_EXPIRE"),
        /**
         * 参数错误
         */
        ERROR_PARAM(3, "ERROR_PARAM");

        ResponseCode(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private Integer code;
        private String desc;

        public Integer getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }


}
