package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.router.CommBackendPageUrlConfig;
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
 * @date 2020-11-26 14:57
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class VisitVO implements Serializable {
    private static final long serialVersionUID = -173120689091202491L;
    private static final String TEMPLATE = "来自<span class=\"dynamic-ip\">%s (ip:%s)</span>的网友在 " +
            "<span class=\"dynamic-date\">%s</span>访问了文章\n"
            + "<span class=\"dynamic-title\"><a href=\"%s\">%s</a></span>";

    /**
     * IP地址
     */
    private String ip;
    /**
     * 访问时间 格式:yyyy-MM-dd HH:mm:ss
     */
    private String visitDate;
    /**
     * 文章标题
     */
    private String articleTitle;
    /**
     * 文章oid
     */
    private Long articleOid;
    /**
     * IP所属地域
     */
    private String area;
    /**
     * 显示描述
     */
    private String desc;

    public String getDesc() {
        return String.format(TEMPLATE, DomainUtil.format(this.area, Constants.UNKNOW),
                DomainUtil.format(this.ip, Constants.UNKNOW), this.visitDate,
                CommBackendPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.articleOid)),
                this.articleTitle);
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

}