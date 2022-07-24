package cn.jianwoo.blog.dto.response.vo;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-15 16:21
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class EmailVO implements Serializable {

    private static final long serialVersionUID = 1152316162401556760L;

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
     * 邮件模板内容,HTML格式
     */
    private String content;
    /**
     * 发送时间
     */
    private String sendTime;

    public String getEmailTos() {
        List<String> emailTos = JSONArray.parseArray(this.emailTos, String.class);
        return StringUtils.join(emailTos, ",");
    }
}
