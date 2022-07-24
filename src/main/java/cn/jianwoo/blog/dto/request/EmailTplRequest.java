package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:00
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class EmailTplRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;


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

}
