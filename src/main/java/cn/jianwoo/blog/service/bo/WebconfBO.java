package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 14:53
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class WebconfBO implements Serializable {
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