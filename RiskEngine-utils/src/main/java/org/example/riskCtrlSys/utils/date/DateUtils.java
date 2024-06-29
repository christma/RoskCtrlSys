package org.example.riskCtrlSys.utils.date;

import sun.util.calendar.ZoneInfo;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(PATTERN);
    }

    public static String convertLocalDateTime2String(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = getFormatter();
        return formatter.format(localDateTime);
    }

    public static LocalDateTime convertString2LocalDateTime(String str) {
        DateTimeFormatter formatter = getFormatter();
        return LocalDateTime.parse(str, formatter);
    }


    public static LocalDateTime convertTimestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static long convertLocalDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

}
