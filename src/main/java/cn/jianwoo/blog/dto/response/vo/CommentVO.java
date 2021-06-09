package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DomainUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130154L;
    private static final String DYNAMIC_TEMPLATE = "来自<span class=\"dynamic-ip\">%s (ip:%s)</span> 的网友 <span class=\"dynamic-user\">[%s]</span> 在 <span class=\"dynamic-date\">%s</span> 评论了文章 \n"
            + "                <span class=\"dynamic-title\"><a href=\"%s\">%s</a></span>: <span class=\"dynamic-comm\">%s</span>";
    private static final String CONSOLE_TEMPLATE = "网友 <span class=\"console-user\">[%s]</span> 在 <span class=\"console-time\">%s</span> 评论了文章 \n"
            + "                <span class=\"console-title\"><a href=\"%s\">%s</a></span>: <span class=\"console-comm\">%s</span>";

    /**
     * seq
     */
    private Integer seq;
    /**
     * 文章标题
     */
    private String artTitle;
    /**
     * 用户名
     */
    private String user;
    /**
     * 评论日期
     */
    private String date;
    /**
     * 回复给的用户
     */
    private String replyTo;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 要回复评论的oid
     */
    private Long replyOid;
    /**
     * 评论oid
     */
    private Long oid;
    /**
     * 文章oid
     */
    private Long artOid;
    /**
     * IP地址
     */
    private String ip;
    /**
     * QQ
     */
    private String qq;
    /**
     * 头像
     */
    private String headImg;
    /**
     * ip所属地域
     */
    private String area;
    /**
     * 是否以读
     */
    private Integer read;
    /**
     * 显示描述
     */
    private String desc = Constants.BLANK;
    private String templateName = "DYNAMIC_TEMPLATE";

    public String getDesc() {
        if ("DYNAMIC_TEMPLATE".equals(templateName)) {
            desc = String.format(DYNAMIC_TEMPLATE, DomainUtil.format(this.area, Constants.UNKNOW),
                    DomainUtil.format(this.ip, Constants.UNKNOW), DomainUtil.format(this.user, Constants.ANAONYMOUS),
                    this.date, CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.artOid)),
                    this.artTitle, this.content);
        } else if ("CONSOLE_TEMPLATE".equals(templateName)) {
            desc = String.format(CONSOLE_TEMPLATE, DomainUtil.format(this.user, Constants.ANAONYMOUS),
                    this.date, CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.artOid)),
                    this.artTitle, this.content);
        }

        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

}