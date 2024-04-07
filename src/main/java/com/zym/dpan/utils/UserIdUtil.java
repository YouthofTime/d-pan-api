package com.zym.dpan.utils;

import java.util.Objects;

/**
 * ClassName: UserIdUtil
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/7 17:42
 * @Version 1.0
 */
public class UserIdUtil {

    public static final Long ZERO_LONG = 0L;

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前登录的用户ID
     *
     * @param value
     */
    public static void set(Long value) {
        threadLocal.set(value);
    }

    /**
     * 获取当前登录的用户ID
     *
     * @return
     */
    public static Long get() {
        Long value = threadLocal.get();
        if (Objects.isNull(value)) {
            return ZERO_LONG;
        }
        return value;
    }

}