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
}
