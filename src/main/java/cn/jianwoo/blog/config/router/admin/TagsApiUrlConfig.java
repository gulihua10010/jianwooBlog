package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-14 19:49
 */
public class TagsApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/tag";
    public static final String URL_TAG_ADD = "/add";
    public static final String URL_TAG_ADD_LIST = "/add/list";
    public static final String URL_TAG_REMOVE = "/remove";
    public static final String URL_TAG_UPDATE = "/update";
    public static final String URL_TAG_LIST = "/list";
    public static final String URL_TAG_ARTICLE_LIST = "/article/list/{artId}";
    public static final String URL_TAG_INFO = "/info/{id}";


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
