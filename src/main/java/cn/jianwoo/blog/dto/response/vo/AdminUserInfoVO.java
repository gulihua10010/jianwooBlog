package cn.jianwoo.blog.dto.response.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-04 11:51
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserInfoVO implements Serializable {
    private static final long serialVersionUID = 8360453789834911774L;

    /**
     * 登录ID
     */
    private String loginID;
    /**
     * 昵称
     */
    private String nickName;
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
    /**
     * 头像
     */
    private String avatarSrc;
}
