package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.page.CommBackendPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DomainUtil;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-26 14:57
 */
public class VisitVO implements Serializable {
    private static final long serialVersionUID = -173120689091202491L;
    private static final String TEMPLATE = "来自<span class=\"dynamic-ip\">%s (ip:%s)</span>的网友在 <span class=\"dynamic-date\">%s</span>访问了文章\n"
            + "                <span class=\"dynamic-title\"><a href=\"%s\">%s</a></span>";

    private String ip;
    private String visitDate;
    private String articleTitle;
    private Long articleOid;
    private String area;
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


    public String getArea() {
        return this.area;
    }


    public void setArea(String area) {
        this.area = area;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getVisitDate() {
        return this.visitDate;
    }


    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }


    public String getArticleTitle() {
        return this.articleTitle;
    }


    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }


    public Long getArticleOid() {
        return this.articleOid;
    }


    public void setArticleOid(Long articleOid) {
        this.articleOid = articleOid;
    }
}
