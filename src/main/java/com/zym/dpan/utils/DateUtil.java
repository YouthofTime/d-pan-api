package com.zym.dpan.utils;

import java.time.LocalDate;

/**
 * ClassName: DateUtil
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/8 21:08
 * @Version 1.0
 */
public class DateUtil {
    private static LocalDate currentDate = LocalDate.now();

    public static String getYear(){
        return String.valueOf(currentDate.getYear());
    }

    public static String getMonth(){
        return String.valueOf(currentDate.getMonth());
    }

    public static String getDay(){
        return String.valueOf(currentDate.getDayOfMonth());
    }
}
