package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-19 14:13
 */
public class MenuApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/menu";
    public static final String URL_MENU_SORT = "/sort";
    public static final String URL_MENU_UPDATE = "/update";
    public static final String URL_MENU_REMOVE = "/remove";
    public static final String URL_MENU_CREATE = "/create";
    public static final String URL_MENU_VALIDATE_SUBMENU = "/validate/submenu";
    public static final String URL_MENU_VALIDATE_ARTICLE_EXIST = "/validate/article/exist";
    public static final String URL_MENU_BACKEND_INFO_LIST = "/backend/info/list";
    public static final String URL_MENU_ARTICLE_CATEGORY_LIST = "/article/category/list";
    public static final String URL_MENU_HOME_LIST = "/home/list";
    public static final String URL_MENU_INFO = "/info/{id}";


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
