package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.util.DateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class BlackIpSummaryVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130157L;

    /**
     * 主键
     */
    private Long oid;
    /**
     * 黑名单IP
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 创建时间
     */
    private String createdTimeStr;

    public String getCreatedTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getCreatedTime());
    }

}