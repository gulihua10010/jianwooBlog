package cn.jianwoo.blog.dto.response.vo;

import cn.jianwoo.blog.util.DateUtil;
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
 * @date 2020-10-29 18:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentListVO implements Serializable {
    private static final long serialVersionUID = 7882315214967519501L;
    private String headImgSrc;
    private String userName;
    private String commentTimeStr;
    private Date commentTime;
    private String content;
    private String parentUserName;
    private Long oid;

    private List<ArticleCommentListVO> replyList;

    public String getCommentTimeDesc() {
        return DateUtil.optimizeTimeDesc(this.getCommentTime());
    }


}
