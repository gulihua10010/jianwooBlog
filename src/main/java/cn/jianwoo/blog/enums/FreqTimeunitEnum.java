package cn.jianwoo.blog.enums;

import java.util.concurrent.TimeUnit;

public enum FreqTimeunitEnum {

    /**
     * 秒
     */
    SECONDS("Second(s)", TimeUnit.SECONDS),
    /**
     * 分钟
     */
    MINUTES("Minutes(s)", TimeUnit.MINUTES),
    /**
     * 小时
     */
    HOURS("Hour(s)", TimeUnit.HOURS),

    ;


    /**
     * TimeUnit字符串
     */
    private String timeunitStr;
    /**
     * TimeUnit
     */
    private TimeUnit timeUnit;

    FreqTimeunitEnum(String timeunitStr, TimeUnit timeUnit) {
        this.timeunitStr = timeunitStr;
        this.timeUnit = timeUnit;
    }

    public String getTimeunitStr() {
        return this.timeunitStr;
    }

    public void setTimeunitStr(String timeunitStr) {
        this.timeunitStr = timeunitStr;
    }

    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public static TimeUnit getTimeUnit(String value) {
        FreqTimeunitEnum[] arry = FreqTimeunitEnum.values();
        for (int i = 0; i < arry.length; i++) {
            if (arry[i].getTimeunitStr().equals(value)) {
                return arry[i].getTimeUnit();
            }
        }
        return null;
    }


}
