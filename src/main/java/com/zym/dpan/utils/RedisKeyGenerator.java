package com.zym.dpan.utils;

/**
 * ClassName: RedisKeyGenerator
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/10 15:17
 * @Version 1.0
 */
public class RedisKeyGenerator {
    private static final String COMMON_SEPARATOR = ":";

    /**
     * 生成分片缓存key，值为文件名后缀（合并时需要生成一个文件）
     * @param identifier
     * @param userId
     * @return
     */
    public static String generateChunkKey(String identifier,Long userId) {
        return new StringBuilder(identifier)
                .append(COMMON_SEPARATOR)
                .append(userId)
                .toString();
    }
}
