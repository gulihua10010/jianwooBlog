package cn.jianwoo.blog.service.bo;

import cn.jianwoo.blog.enums.FreqTimeunitEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 频率BO
 *
 * @author gulihua
 * @date 2022-03-12 23:12
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class FrequencyBO implements Serializable {

    /**
     * 次数
     */
    private Integer times;
    /**
     * 时间
     */
    private Integer time;
    /**
     * 时间单位
     */
    private TimeUnit timeUnit;
    /**
     * 是否不限
     */
    private Boolean isNoLimit;

    public FrequencyBO(Boolean isNoLimit) {
        this.isNoLimit = isNoLimit;
    }

    public FrequencyBO(Integer times, Integer time, TimeUnit timeUnit) {
        this.times = times;
        this.time = time;
        this.timeUnit = timeUnit;
        this.isNoLimit = false;
    }

    public FrequencyBO(String times, String time, String timeUnit) {
        this(Integer.parseInt(times), Integer.parseInt(time), FreqTimeunitEnum.getTimeUnit(timeUnit));

    }
}
