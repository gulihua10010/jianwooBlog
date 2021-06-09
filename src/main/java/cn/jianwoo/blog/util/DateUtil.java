package cn.jianwoo.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 19:05
 */
@Slf4j
public class DateUtil extends cn.hutool.core.date.DateUtil {

    //date format
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_TIMESTAMP = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";


    public static Date getNow() {
        return new Date();
    }

    public static String formatTimestamp(Date date) {
        if (null == date)
            return "";
        return cn.hutool.core.date.DateUtil.format(date, DATE_FORMAT_YYYYMMDDHHMMSS_TIMESTAMP);
    }

    public static Date parseTimestamp(String timestamp) {
        if (StringUtils.isBlank(timestamp))
            return null;
        return cn.hutool.core.date.DateUtil.parse(timestamp, DATE_FORMAT_YYYYMMDDHHMMSS_TIMESTAMP);
    }

    public static String getNowTimestamp() {
        return formatTimestamp(getNow());
    }

    public static String getStandardFormat(Date date) {
        if (null == date)
            return "";
        return cn.hutool.core.date.DateUtil.format(date, DATE_FORMAT_YYYYMMDDHHMMSS);
    }


    public static String getNowStandardFormat() {
        return getStandardFormat(getNow());
    }


    /**
     * 时间的加法
     *
     * @param date     时间
     * @param timeout  过期时间
     * @param timeUnit timeUnit
     * @return
     * @author gulihua
     */
    public static Date add(@NonNull Date date, long timeout, @NonNull TimeUnit timeUnit) {

        Date result;
        int timeIntValue;
        if (timeout > Integer.MAX_VALUE) {
            timeIntValue = Integer.MAX_VALUE;
        } else {
            timeIntValue = Long.valueOf(timeout).intValue();
        }
        switch (timeUnit) {
            case DAYS:
                result = org.apache.commons.lang3.time.DateUtils.addDays(date, timeIntValue);
                break;
            case HOURS:
                result = org.apache.commons.lang3.time.DateUtils.addHours(date, timeIntValue);
                break;
            case MINUTES:
                result = org.apache.commons.lang3.time.DateUtils.addMinutes(date, timeIntValue);
                break;
            case SECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addSeconds(date, timeIntValue);
                break;
            case MILLISECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, timeIntValue);
                break;
            default:
                result = date;
        }
        return result;

    }
}
