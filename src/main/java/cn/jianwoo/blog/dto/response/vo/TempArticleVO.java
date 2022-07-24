package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class TempArticleVO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
    @JSONField(serializeUsing = LongToStringSerializerConfig.class)
    private Long oid;
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
     * 类别id
     */
    private Integer categoryId;
    /**
     * 缩略图
     */
    private String imgSrc;
    /**
     * 状态
     */
    private String status;
    /**
     * 访问类型
     */
    private String accessType;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否可以评论
     */
    private Boolean isComment;

    /**
     * 文章标签列表
     */
    private List<TagsVO> artTagsList;


    /**
     * 是否置顶
     */
    private Boolean topPlaceFlag;


    /**
     * 是否原创
     */
    private Boolean flagOriginal;

    /**
     * 转载源链接
     */
    private String originalUrl;

}
