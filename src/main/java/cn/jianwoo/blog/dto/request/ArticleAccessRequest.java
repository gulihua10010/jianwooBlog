package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-07-18 0:56
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
public class ArticleAccessRequest extends BaseRequestDto implements Serializable {
    private static final long serialVersionUID = -7982293393643864915L;

    /**
     * 文章主键OID
     */
    private String artOid;


}
