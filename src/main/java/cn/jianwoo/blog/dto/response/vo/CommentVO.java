package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.page.CommBackendPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DomainUtil;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 11:59
 */
public class CommentVO implements Serializable {

    private static final long serialVersionUID = 2152295702147130154L;
    private static final String TEMPLATE = "来自<span class=\"dynamic-ip\">%s (ip:%s)</span>的网友 <span class=\"dynamic-user\">[%s]</span> 在 <span class=\"dynamic-date\">%s</span>评论了文章\n"
            + "                <span class=\"dynamic-title\"><a href=\"%s\">%s</a></span>: <span class=\"dynamic-comm\">%s</span>";
    private Integer seq;
    private String artTitle;
    private String user;
    private String date;
    private String replyTo;
    private String content;
    private Long replyOid;
    private Long oid;
    private Long artOid;
    private String ip;
    private String area;
    private String desc;

    public String getDesc() {
        return String.format(TEMPLATE, DomainUtil.format(this.area, Constants.UNKNOW),
                DomainUtil.format(this.ip, Constants.UNKNOW), DomainUtil.format(this.user, Constants.ANAONYMOUS),
                this.date, CommBackendPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.artOid)),
                this.artTitle, this.content);
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getIp() {
        return this.ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getArea() {
        return this.area;
    }


    public void setArea(String area) {
        this.area = area;
    }


    public Long getArtOid() {
        return artOid;
    }


    public void setArtOid(Long artOid) {
        this.artOid = artOid;
    }


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public Long getReplyOid() {
        return replyOid;
    }


    public void setReplyOid(Long replyOid) {
        this.replyOid = replyOid;
    }


    public Integer getSeq() {
        return seq;
    }


    public void setSeq(Integer seq) {
        this.seq = seq;
    }


    public String getArtTitle() {
        return artTitle;
    }


    public void setArtTitle(String artTitle) {
        this.artTitle = artTitle;
    }


    public String getUser() {
        return user;
    }


    public void setUser(String user) {
        this.user = user;
    }


    public String getDate() {
        return date;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public String getReplyTo() {
        return replyTo;
    }


    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
