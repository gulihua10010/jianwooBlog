package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BasePageRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 16:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebconfRequest extends BasePageRequestDto {
    private static final long serialVersionUID = -8260719513799536531L;
    /**
     * 网站标题
     */
    private String title;
    /**
     * 网站作者
     */
    private String author;
    /**
     * 网站关键词
     */
    private String keywords;
    /**
     * 网站描述
     */
    private String description;
    /**
     * 网站备案
     */
    private String record;
    /**
     * 网站域名
     */
    private String domain;
    /**
     * 网站底部html
     */
    private String footHtml;
    /**
     * 网站logo
     */
    private String logoImg;
    /**
     * 网站主页顶部图片
     */
    private String homeImg;
    /**
     * 文章每页显示
     */
    private Integer numPerPage;
    /**
     * 是否可以评论
     */
    private Boolean isComment;

    /**
     * 登录是否需要验证码
     */
    private Boolean isCaptchaOn;
}