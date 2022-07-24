package cn.jianwoo.blog.dto.response.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-15 16:21
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class EmailTplVO implements Serializable {

    private static final long serialVersionUID = 1152316162401556760L;
    /**
     * 主键
     */
    private Long oid;
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
     * 邮件模板内容,HTML格式
     */
    private String content;

    /**
     * 测试的JSON数据
     */
    private String testJsonData;

    /**
     * 创建时间
     */
    private String createTime;
}
