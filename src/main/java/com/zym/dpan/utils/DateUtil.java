package com.zym.dpan.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateUtil
 * Package: com.zym.dpan.utils
 *
 * @Author zym
 * @Create 2024/4/8 21:08
 * @Version 1.0
 */
public class DateUtil {

    public static String getYear(){
        return String.valueOf(LocalDate.now().getYear());
    }

    public static String getMonth(){
        return String.valueOf(LocalDate.now().getMonth());
    }

    public static String getDay(){
        return String.valueOf(LocalDate.now().getDayOfMonth());
    }

    public static Date afterDays(Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static LocalDate afterDays1(Integer days) {
        LocalDate currentDate = LocalDate.now();
        return currentDate.plusDays(days);
    }

    public static void main(String[] args) {
        System.out.println(afterDays(1));
        System.out.println(afterDays1(1));
    }
}
