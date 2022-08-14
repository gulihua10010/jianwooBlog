package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import cn.jianwoo.blog.util.DateUtil;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ArticleMainVO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
    @JSONField(serializeUsing = LongToStringSerializerConfig.class)
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者
     */
    private String author;

    /**
     * 文章发布时间
     */
    private Date publishTime;

    /**
     * 文章发布的地区
     */
    private String publishRegion;
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
     * 文章内容
     */
    private String content;
    /**
     * 类别id
     */
    private Integer categoryOid;

    /**
     * 文章权限
     */
    private String permission;

    /**
     * 文章类型
     */
    private String category;

    /**
     * 上级文章类型
     */
    private String parentCategory;

    /**
     * 上级类别id
     */
    private Integer parentCategoryOid;

    /**
     * 缩略图
     */
    private String imgSrc;

    /**
     * 评论数量
     */
    private Long commentCount;

    /**
     * 阅读次数
     */
    private Long readCount;

    /**
     * 赞的数量
     */
    private Long praiseCount;
    /**
     * 文章标签列表
     */
    private List<TagsVO> tags;

    /**
     * 是否评论
     */
    private Boolean isComment;

    /**
     * 是否原创
     */
    private Boolean flagOriginal;

    /**
     * 转载源链接
     */
    private String originalUrl;

    /**
     * 当前IP是否已经赞过
     */
    private Boolean isPraise;

    /**
     * 文章是否是锁的状态(需要密码才能访问)
     */
    private Boolean isLock;

    public String getPublishTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getPublishTime());
    }

    public String getModifiedTimeStr() {
        return DateUtil.optimizeTimeDesc(this.getModifiedTime());
    }
}
