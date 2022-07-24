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
public class EmailSendRequest extends BaseRequestDto {

    private static final long serialVersionUID = -9159381492520980551L;

    /**
     * 登录名
     */
    private String loginId;
    /**
     * 邮箱地址
     */
    private String email;

}
