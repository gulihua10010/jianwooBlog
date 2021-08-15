package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.config.LongToStringSerializerConfig;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
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
     * 菜单主键
     */
    private Integer menuOid;
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
    private String visitType;
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


}
