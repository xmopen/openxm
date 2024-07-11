package cn.openxm.common.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalTime 本地时间工具库。
 *
 * @author Xiao Ma
 * @date 2024/7/11
 * @slogan 少年应有鸿鹄志，当骑骏马踏平川。
 */
public class LocalTime {

    /**
     * FORMATTER_BASIC_LOCAL_TIME 本地时间格式。
     * */
    public static final String FORMATTER_BASIC_LOCAL_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * getCurrenTimeWithSecond 获取当前时间戳，以秒来表示。
     * */
    public static Long getCurrenTimeWithSecond(){
        return Instant.now().getEpochSecond();
    }

    /**
     * getCurrenTimeWithMillis 获取当前时间的毫秒表示形式。
     * */
    public static Long getCurrenTimeWithMillis(){
        return System.currentTimeMillis();
    }

    /**
     * getCurrentTimeWithFormatter 通过给定的时间格式返回其目标时间。
     * */
    public static String getCurrentTimeWithFormatter(String formatter){
        LocalDateTime now = LocalDateTime.now();
        return now.format(DateTimeFormatter.ofPattern(formatter));
    }

}
