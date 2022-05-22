package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class EmailBO implements Serializable {
    private static final long serialVersionUID = -7982293393643864915L;

    /**
     * 主键OID
     */
    private Long oid;

    /**
     * 收件人
     */
    private String toEmail;

    /**
     * 邮件模板编号
     */
    private String emailTplCode;

    /**
     * 邮件主题
     */
    private String subject;


    /**
     * 邮件正文内容
     */
    private String content;

    /**
     * 邮件组装的json参数
     */
    private String jsonData;

    /**
     * 邮件附件(JSONArray格式)
     */
    private String files;

    /**
     * 处理状态(00:未处理,10:正在发送,90:发送成功,91:发送失败)
     */
    private String procStatus;

    /**
     * 处理结果描述
     */
    private String procDesc;

    /**
     * 处理时间
     */
    private Date procTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
