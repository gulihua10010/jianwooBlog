package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.lang.NonNull;

import java.util.Calendar;
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
    public static final String DATE_FORMAT_MMDDHHMM = "MM月dd日 HH:mm";
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy年MM月dd日 HH:mm";


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

    /**
     * 优化时间表述/<br>
     * date - currentTime < 60秒: 刚刚<br>
     * date - currentTime < 60分钟: n分钟前<br>
     * date - currentTime < 24小时: n小时前<br>
     * 1天 < date - currentTime < 2天: 昨天<br>
     * date.year == currentTime.year即在今年: MM月dd日 HH:mm<br>
     * date.year != currentTime.year即不在今年: yyyy年MM月dd日 HH:mm<br>
     *
     * @param date date
     * @return
     * @author gulihua
     */
    public static String optimizeTimeDesc(Date date) {
        if (null == date) {
            return Constants.BLANK;
        }
        //计算到秒
        long currTs = System.currentTimeMillis() / 1000;
        long ts = date.getTime() / 1000;
        if (currTs - ts < 60 && currTs - ts > 0) {
            return "刚刚";
        }
        //计算到分钟
        currTs = currTs / 60;
        ts = ts / 60;
        if (currTs - ts < 60 && currTs - ts > 0) {
            return (currTs - ts) + "分钟前";
        }
        //计算到小时
        currTs = currTs / 60;
        ts = ts / 60;
        if (currTs - ts < 24 && currTs - ts > 0) {
            return (currTs - ts) + "小时前";
        }
        //计算到天
        currTs = currTs / 24;
        ts = ts / 24;
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date());
        c2.add(Calendar.DATE, -1);
        if (c2.get(Calendar.DATE) == c1.get(Calendar.DATE)) {
            return "昨天";
        }
        if (c2.get(Calendar.YEAR) == c1.get(Calendar.YEAR)) {
            return cn.hutool.core.date.DateUtil.format(date, DATE_FORMAT_MMDDHHMM);
        }

        return cn.hutool.core.date.DateUtil.format(date, DATE_FORMAT_YYYYMMDDHHMM);


    }
}
