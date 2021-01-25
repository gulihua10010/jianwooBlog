package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2020-10-29 18:56
 */

@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class CommentReplyListVO implements Serializable {
    private static final long serialVersionUID = 4418335823488391116L;
    private String headImg;
    private String user;
    private String date;
    private String content;
    private String parentUserName;
    private Long oid;

}