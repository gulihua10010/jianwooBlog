package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-05 17:07
 */
public class UserApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/user";
    public static final String URL_USER_INFO = "/info";
    public static final String URL_USER_PROFILE_EDIT = "/profile/edit";
    public static final String URL_USER_PROFILE_EDIT_UPDATE = "/profile/edit/update";
    public static final String URL_USER_PASSWORD_CHANGE = "/password/change";
    public static final String URL_USER_FORGET_PASSWORD_CHECK = "/forget/password/check";
    public static final String URL_USER_FORGET_PASSWORD_CHANGE = "/forget/password/change";


    /**
     * 获取默认的url
     *
     * @param url url
     * @return
     * @author gulihua
     */
    public static String getUrl(String url) {
        return getUrl(url, DEFAULT_VERSION);
    }

    /**
     * 获取指定版本的url
     *
     * @param url     url
     * @param version 版本
     * @return
     * @author gulihua
     */
    public static String getUrl(String url, int version) {
        return getUrl(URL_PREFIX, url, version);
    }
}
