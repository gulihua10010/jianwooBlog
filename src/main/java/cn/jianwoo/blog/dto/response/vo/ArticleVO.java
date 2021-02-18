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
 * @date 2020-11-02 17:58
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO implements Serializable {
    private static final long serialVersionUID = -8183712030536923735L;
    private static final String TEMPLATE = "<span class=\"console-author\">%s</span> 在 " +
            "<span class=\"console-time\">%s</span> 发表了 " +
            "<span class=\"console-title\"><a href=\"%s\">%s</a></span>";
    /**
     * 文章OID
     */
    private Long oid;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布时间 yyyy-MM-dd HH:mm:ss格式
     */
    private String publishDate;
    /**
     * 文章最后修改时间 yyyy-MM-dd HH:mm:ss格式
     */
    private String modifiedDate;
    /**
     * 文章作者
     */
    private String author;
    /**
     * 文章类型
     */
    private String type;
    /**
     * 文章状态 (0:草稿, 1:已发布, -1:回收站 )
     */
    private Integer status;
    /**
     * 文章显示描述
     */
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

}
