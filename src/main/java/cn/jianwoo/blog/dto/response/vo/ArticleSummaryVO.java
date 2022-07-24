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
 * @date 2020-11-02 17:58
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ArticleSummaryVO implements Serializable {
    private static final long serialVersionUID = -8183712030536923735L;
    private static final String TEMPLATE = "<span class=\"console-author\">%s</span> 在 " +
            "<span class=\"console-time\">%s</span> 发表了 " +
            "<span class=\"console-title\"><a href=\"%s\">%s</a></span>";
    /**
     * 文章OID
     */
    @JSONField(serializeUsing = LongToStringSerializerConfig.class)
    private Long oid;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章发布时间
     */
    private Date publishTime;
    /**
     * 文章发布时间 yyyy-MM-dd HH:mm:ss格式
     */
    private String publishTimeStr;
    /**
     * 文章最后修改时间
     */
    private Date modifiedTime;
    /**
     * 文章最后修改时间 yyyy-MM-dd HH:mm:ss格式
     */
    private String modifiedTimeStr;

    /**
     * 文章删除时间
     */
    private Date delTime;
    /**
     * 文章删除时间 yyyy-MM-dd HH:mm:ss格式
     */
    private String delTimeStr;
    /**
     * 文章作者
     */
    private String author;
    /**
     * 文章类型
     */
    private String category;
    /**
     * 文章状态 (00:草稿, 90:已发布, 91:回收站 )
     */
    private String status;
    /**
     * 文章显示描述
     */
    private String desc;

    /**
     * 回收站删除时间
     */
    private Date removeRecycleTime;

    public String getDesc() {
        return String.format(TEMPLATE, DomainUtil.format(this.author, Constants.ANAONYMOUS),
                this.getPublishTimeDesc(),
                CommAdminPageUrlConfig.URL_PREFIX + CommAdminPageUrlConfig.URL_ARTICLE_EDIT.replace("{id}", String.valueOf(this.oid)),
                this.title);
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getPublishTime());
    }

    public String getDelTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getDelTime());
    }

    public String getRemoveRecycleTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getRemoveRecycleTime());
    }

}
