package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description 定义Admin页面的URL
 * @date 2020-08-05 17:07
 */
public class CommAdminPageUrlConfig extends UrlConfig {

    public static final String URL_PREFIX = "/admin";

    public static final String URL_INDEX = "/index";
    public static final String URL_ARTICLE_EDIT = "#/article/edit/id={id}";

    public static final String URL_LOGIN = "/passport/login";
    public static final String URL_PASSWORD_FORGET = "/passport/password/forget";
    public static final String URL_PASSWORD_CHANGE = "/passport/password/change";
    public static final String URL_PASSPORT= "/passport";
    public static final String URL_ERROR = "/error";
    public static final String PAGE_ADMIN_LOGIN = "/passport#login";


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
