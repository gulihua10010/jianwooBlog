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
public class EmailTplSummaryVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130157L;

    /**
     * 邮件模板编号
     */
    private String code;
    /**
     * 描述
     */
    private String desc;
    /**
     * 邮件模板主题
     */
    private String subject;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建时间
     */
    private String createTimeStr;

    public String getCreateTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getCreateTime());
    }

}