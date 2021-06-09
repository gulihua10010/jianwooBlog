package cn.jianwoo.blog.security.token;

import lombok.Data;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-25 16:23
 */
@Data
public class AuthToken {

    /**
     * USER OID
     */
    private Long uid;

    /**
     * Access token.
     */
    private String accessToken;
    /**
     * Expired in. (seconds)
     */
    private Long expiredIn;
    /**
     * Refresh token.
     */
    private String refreshToken;

}
