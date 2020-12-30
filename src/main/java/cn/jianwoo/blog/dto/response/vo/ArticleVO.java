package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.page.CommBackendPageUrlConfig;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.util.DomainUtil;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-02 17:58
 */
public class ArticleVO implements Serializable {
    private static final long serialVersionUID = -8183712030536923735L;
    private static final String TEMPLATE = "<span class=\"console-author\">%s</span> 在 " +
            "<span class=\"console-time\">%s</span> 发表了 " +
            "<span class=\"console-title\"><a href=\"%s\">%s</a></span>";
    private Long oid;
    private String title;
    private String publishDate;
    private String modifiedDate;
    private String author;
    private String type;
    private Integer status;
    private String desc;

    public String getDesc() {
        return String.format(TEMPLATE, DomainUtil.format(this.author, Constants.ANAONYMOUS),
                this.publishDate,
                CommBackendPageUrlConfig.URL_PREFIX + CommBackendPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.oid)),
                this.title);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getModifiedDate() {
        return modifiedDate;
    }


    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }


    public Long getOid() {
        return oid;
    }


    public void setOid(Long oid) {
        this.oid = oid;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getPublishDate() {
        return publishDate;
    }


    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }


    public String getAuthor() {
        return author;
    }


    public void setAuthor(String author) {
        this.author = author;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
}
