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
 * @date 2020-10-29 18:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class CommentListVO implements Serializable {
    private static final long serialVersionUID = 7882315214967519501L;
    private String headImg;
    private String user;
    private String date;
    private String content;
    private Long oid;

    private List<CommentReplyListVO> replyList;



}
