package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import cn.jianwoo.blog.config.router.admin.CommAdminPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.DomainUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class CommentSummaryVO implements Serializable {

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
     * 用户昵称
     */
    private String userNick;

    /**
     * 评论日期
     */
    private Date commentTime;
    /**
     * 评论日期 格式:yyyy-MM-dd HH:mm:ss
     */
    private String commentTimeStr;
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
    @JSONField(serializeUsing = LongToStringSerializerConfig.class)
    private Long artOid;
    /**
     * IP地址
     */
    private String clientIp;
    /**
     * ip所属地域
     */
    private String userRegion;
    /**
     * 评论文章是否删除
     */
    private String artDelStatus;
    /**
     * 评论是否删除
     */
    private String isDelete;
    /**
     * 显示描述
     */
    private String desc = Constants.BLANK;
    private String templateName = "DYNAMIC_TEMPLATE";

    public String getDesc() {
        if ("DYNAMIC_TEMPLATE".equals(templateName)) {
            desc = String.format(DYNAMIC_TEMPLATE, DomainUtil.format(this.userRegion, Constants.UNKNOW),
                    DomainUtil.format(this.clientIp, Constants.UNKNOW), DomainUtil.format(this.userNick, Constants.ANAONYMOUS),
                    DateUtil.optimizeTimeDesc(this.commentTime), CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.artOid)),
                    this.artTitle, this.content);
        } else if ("CONSOLE_TEMPLATE".equals(templateName)) {
            desc = String.format(CONSOLE_TEMPLATE, DomainUtil.format(this.userNick, Constants.ANAONYMOUS),
                    DateUtil.optimizeTimeDesc(this.commentTime), CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.artOid)),
                    this.artTitle, this.content);
        }

        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getCommentTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getCommentTime());
    }

}