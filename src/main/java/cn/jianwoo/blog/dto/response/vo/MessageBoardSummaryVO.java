package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.DomainUtil;
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
public class MessageBoardSummaryVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130154L;
    private static final String DYNAMIC_TEMPLATE = "来自<span class=\"dynamic-ip\">%s (ip:%s)</span> 的网友 <span class=\"dynamic-user\">[%s]</span> 在 <span class=\"dynamic-date\">%s</span> 给博客留了言 \n"
            + "                : <span class=\"dynamic-msg\">%s</span>";
    private static final String CONSOLE_TEMPLATE = "网友 <span class=\"console-user\">[%s]</span> 在 <span class=\"console-time\">%s</span> 给博客留了言 \n"
            + "                : <span class=\"console-msg\">%s</span>";

    /**
     * seq
     */
    private Integer seq;
    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 留言日期
     */
    private Date pushTime;
    /**
     * 留言日期 格式:yyyy-MM-dd HH:mm:ss
     */
    private String pushTimeStr;
    /**
     * 回复给的用户
     */
    private String replyTo;
    /**
     * 留言内容
     */
    private String content;
    /**
     * 要回复留言的oid
     */
    private Long replyOid;
    /**
     * 留言oid
     */
    private Long oid;
    /**
     * IP地址
     */
    private String clientIp;
    /**
     * ip所属地域
     */
    private String userRegion;
    /**
     * 留言是否删除
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
                    DateUtil.optimizeTimeDesc(this.getPushTime()),
                    this.content);
        } else if ("CONSOLE_TEMPLATE".equals(templateName)) {
            desc = String.format(CONSOLE_TEMPLATE, DomainUtil.format(this.userNick, Constants.ANAONYMOUS),
                    DateUtil.optimizeTimeDesc(this.getPushTime()),
                    this.content);
        }

        return desc;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getPushTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getPushTime());
    }

}