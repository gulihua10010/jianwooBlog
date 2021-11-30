package cn.jianwoo.blog.dto.request;

import cn.jianwoo.blog.base.BaseRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-15 16:00
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserEditInfoRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 登录ID
     */
    private String loginID;
    /**
     * 昵称
     */
    private String userNick;
    /**
     * 昵称
     */
    private String userEmail;
    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 性别[10:男, 20:女]'
     */
    private String userSex;

}
