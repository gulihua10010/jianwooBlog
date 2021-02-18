package cn.jianwoo.blog.dto.response.vo;

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
public class ArticleInfoVO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键
     */
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
     * 缩略图
     */
    private String imgSrc;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 访问类型
     */
    private Integer visitType;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否可以评论
     */
    private Integer isComment;


}
