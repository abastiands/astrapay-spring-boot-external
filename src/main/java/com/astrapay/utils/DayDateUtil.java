package com.astrapay.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DayDateUtil {
    private DayDateUtil() {}

    public static String dateNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
