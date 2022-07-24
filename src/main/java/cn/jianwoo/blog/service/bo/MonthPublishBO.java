package cn.jianwoo.blog.service.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:57
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class MonthPublishBO implements Serializable {
    private static final long serialVersionUID = -173120689091202491L;
    /**
     * 发布的日期(yyyy-MM-dd)
     */
    private String date;

    /**
     * 发布的日期
     */
    private Integer day;
    /**
     * 当天发布文章的数量
     */
    private Integer count;

}