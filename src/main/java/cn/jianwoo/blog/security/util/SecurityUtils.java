package cn.jianwoo.blog.security.util;

import cn.jianwoo.blog.service.bo.UserBO;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-23 21:39
 */
public class SecurityUtils {


    /**
     * Access token cache 前缀.
     */
    public final static String TOKEN_ACCESS_CACHE_PREFIX = "jianwoo.admin.access.token.";

    /**
     * Refresh token cache 前缀.
     */
    public final static String TOKEN_REFRESH_CACHE_PREFIX = "jianwoo.admin.refresh.token.";

    /**
     * Admin token 名.
     */
    public final static String ADMIN_TOKEN_HEADER_NAME = "JIANWOO_ADMIN_" + HttpHeaders.AUTHORIZATION;

    public SecurityUtils() {
    }

    public static String buildAccessTokenKey(@NonNull UserBO user) {
        return TOKEN_ACCESS_CACHE_PREFIX + user.getId();
    }

    public static String buildRefreshTokenKey(@NonNull UserBO user) {
        return TOKEN_REFRESH_CACHE_PREFIX + user.getId();
    }

    public static String buildTokenAccessKey(@NonNull String accessToken) {
        return TOKEN_ACCESS_CACHE_PREFIX + accessToken;
    }

    public static String buildTokenRefreshKey(@NonNull String refreshToken) {
        return TOKEN_REFRESH_CACHE_PREFIX + refreshToken;
    }
}
