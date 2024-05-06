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
    public static final String COMMON_SEPARATOR = "__,__";
    public static final String EMPTY_STR = "";
    public static final String POINT_STR = ".";
    public static final String SLASH_STR = "/";
    public static final Long ZERO_LONG = 0L;
    public static final Integer ZERO_INT = 0;
    public static final Integer ONE_INT = 1;
    public static final Integer TWO_INT = 2;
    public static final Integer SEVEN_INT = 7;
    public static final Integer MINUS_ONE_INT = -1;
    public static final String TRUE_STR = "true";

    public static final Long ONE_DAY_LONG = 24L * 60L * 60L * 1000L;
    public static final Long FIVE_MINUTES_LONG = 5L * 60L * 1000L;
    public static final Long ONE_HOUR_LONG = 60L * 60L * 1000L;

    public static final Long ONE_DAY_SECONDS = 24L * 60L * 60L;
    public static final Long FIVE_MINUTES_SECONDS = 5L * 60L;

    public static final String DEFAULT_SHARE_PREFIX = "http://127.0.0.1:7001/share/";

    /**
     * 登录用户ID的key
     */
    public static final String LOGIN_USER_ID = "LOGIN_USER_ID";

    /**
     * 分享ID
     */
    public static final String SHARE_ID = "SHARE_ID";

    /**
     * 用户登录后存储redis的key前缀
     */
    public static final String USER_LOGIN_PREFIX = "USER_LOGIN_";
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
