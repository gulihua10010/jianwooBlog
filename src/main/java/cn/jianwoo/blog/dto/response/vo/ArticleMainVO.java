package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
     * 文章内容
     */
    private String content;
    /**
     * 菜单主键
     */
    private Integer menuOid;

    /**
     * 文章权限
     */
    private String permission;

    /**
     * 文章类型
     */
    private String category;

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


}
