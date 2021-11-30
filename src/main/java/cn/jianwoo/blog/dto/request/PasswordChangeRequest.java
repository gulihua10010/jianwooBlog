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
public class PasswordChangeRequest extends BaseRequestDto {
    private static final long serialVersionUID = 1691429969857619376L;

    /**
     * 登录ID
     */
    private String loginID;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;

}
