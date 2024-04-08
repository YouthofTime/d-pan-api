package com.zym.dpan.utils;

import java.util.UUID;

/**
 * ClassName: UUIDUtil
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/8 21:22
 * @Version 1.0
 */
public class UUIDUtil {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").toLowerCase();
    }
}
