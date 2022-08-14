package cn.jianwoo.blog.dto.response.vo;

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
public class WebPageConfigVO implements Serializable {
    private static final long serialVersionUID = -173120689091202491L;
    ;

    /**
     * 站点标题
     */
    private String title;

    /**
     * 网站关键字
     */
    private String keywords;

    /**
     * 网站描述
     */
    private String desc;

    /**
     * 网站作者
     */
    private String author;

    /**
     * 网站页脚html信息
     */
    private String footHtml;

    /**
     * 网站域名备案
     */
    private String record;

    /**
     * 网站备案链接
     */
    private String recordUrl;

    /**
     * 网站域名
     */
    private String domain;
}