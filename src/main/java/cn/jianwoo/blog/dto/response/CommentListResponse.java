package cn.jianwoo.blog.dto.response;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.dto.response.vo.ArticleCommentListVO;
import cn.jianwoo.blog.dto.response.vo.UserInfoVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-12 12:00
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CommentListResponse extends BaseResponseDto {

    private static final long serialVersionUID = -4171515102409676030L;
    /**
     * 当前IP用户信息
     */
    private UserInfoVO userInfo;
    /**
     * 评论集合
     */
    private List<ArticleCommentListVO> commentList;
    /**
     * 总记录数
     */
    private Long count;
    /**
     * 根评论记录数
     */
    private Long rootCommCount;

    public static CommentListResponse getInstance() {
        return new CommentListResponse();
    }


}
