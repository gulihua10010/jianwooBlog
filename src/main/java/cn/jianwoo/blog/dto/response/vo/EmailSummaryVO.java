package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class EmailSummaryVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130157L;

    /**
     * 主键
     */
    private Long oid;
    /**
     * 收件人
     */
    private String emailTos;
    /**
     * 邮件模板编号
     */
    private String code;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 处理状态
     */
    private String processStatus;
    /**
     * 发送时间
     */
    private Date sendTime;
    /**
     * 发送时间
     */
    private String sendTimeStr;

    public String getSendTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getSendTime());
    }

    public String getEmailTos() {
        List<String> emailTos = JSONArray.parseArray(this.emailTos, String.class);
        return StringUtils.join(emailTos, ",");
    }
}