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
 * @date 2020-11-02 17:58
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ArticleMainPageVO implements Serializable {
    private static final long serialVersionUID = -8183712030536923735L;
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
     * 文章作者
     */
    private String author;

    /**
     * 文章类型
     */
    private String category;

    /**
     * 文章权限
     */
    private String permission;

    /**
     * 缩略图
     */
    private String imgSrc;

    /**
     * 文章简略描述
     */
    private String desc;

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
     * 当前IP是否已经赞过
     */
    private Boolean isPraise;

    /**
     * 标签列表
     */
    private List<TagsVO> tags;

    /**
     * 是否置顶
     */
    private Boolean topPlaceFlag;

    public String getPublishTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getPublishTime());
    }

}
