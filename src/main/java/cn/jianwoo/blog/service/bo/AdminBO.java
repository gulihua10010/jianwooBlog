package cn.jianwoo.blog.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author GuLihua
 * @Description
 * @date 2021-02-18 15:52
 */
@Data
@EqualsAndHashCode()
@NoArgsConstructor
@AllArgsConstructor
public class AdminBO implements Serializable {
    private static final long serialVersionUID = -6622957226309536544L;
    /**
     * 主键OID
     */
    private Long oid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String userNick;

    /**
     * 手机号码
     */
    private String userPhone;

    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 性别[10:男, 20:女]'
     */
    private String userSex;

    /**
     * 头像
     */
    private String avatarSrc;

    /**
     * 注册IP地址
     */
    private String registerIp;

    /**
     * 注册地区
     */
    private String registerArea;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录地区
     */
    private String lastLoginArea;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

}
