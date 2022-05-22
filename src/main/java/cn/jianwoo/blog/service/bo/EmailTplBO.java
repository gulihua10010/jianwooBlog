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
public class EmailTplBO implements Serializable {
    private static final long serialVersionUID = -7982293393643864915L;

    /**
     * 邮件模板编号
     */
    private String emailTplCode;

    /**
     * 邮件模板内容,HTML格式
     */
    private String content;
    /**
     * 邮件模板主题
     */
    private String subject;
    /**
     * 描述
     */
    private String desc;
    /**
     * 测试的JSON数据
     */
    private String testJsonData;
    /**
     * 创建时间
     */
    private Date createTime;


}
