package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-25 15:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class MessageBoardPraiseRequest extends BaseRequestDto {

    private static final long serialVersionUID = -9159381492520980551L;

    /**
     * 留言oid
     */
    private String msgOid;

}
