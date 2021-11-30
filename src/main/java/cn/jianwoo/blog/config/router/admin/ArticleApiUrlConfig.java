package cn.jianwoo.blog.config.router.admin;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-25 15:52
 */
public class ArticleApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/admin/article";
    public static final String URL_ARTICLE_PUBLISHED = "/published";
    public static final String URL_ARTICLE_SAVE_DRAFT = "/save/draft";
    public static final String URL_ARTICLE_SAVE_RECYCLE = "/save/recycle";
    public static final String URL_ARTICLE_UPDATE = "/update";
    public static final String URL_ARTICLE_DRAFT_STATUS_PUBLISH = "/draft/status/publish";
    public static final String URL_ARTICLE_INFO_UPDATE = "/info/update";
    public static final String URL_ARTICLE_DRAFT_SAVE = "/draft/save";
    public static final String URL_ARTICLE_SAVE_AND_REMOVE_RECYCLE = "/save/remove/recycle";
    public static final String URL_ARTICLE_DRAFT_PUBLISH = "/draft/publish";
    public static final String URL_ARTICLE_EFFECTIVE_LIST = "/effective/list";
    public static final String URL_ARTICLE_RECYCLE_LIST = "/recycle/list";
    public static final String URL_ARTICLE_REMOVE_RECYCLE_LIST = "/remove/recycle/list";
    public static final String URL_ARTICLE_RECYCLE_RESTORE_DRAFT_LIST = "/recycle/restore/draft/list";
    public static final String URL_ARTICLE_DELETE_RECYCLE_LIST = "/delete/recycle/list";
    public static final String URL_ARTICLE_RECYCLE_RESTORE_DRAFT = "/recycle/restore/draft";
    public static final String URL_ARTICLE_RECYCLE_DELETE = "/recycle/delete";
    public static final String URL_ARTICLE_REMOVE_RECYCLE = "/remove/recycle";
    public static final String URL_ARTICLE_INFO = "/info/{id}";
    public static final String URL_ARTICLE_TEMP_SAVE = "/temp/save";
    public static final String URL_ARTICLE_TEMP_UPDATE = "/temp/update";
    public static final String URL_ARTICLE_LAST_TEMP_INFO = "/last/temp/info";
    public static final String URL_ARTICLE_TEMP_UPDATE_RESTORE = "/temp/update/restore";
    public static final String URL_ARTICLE_TEMP_UPDATE_VOID = "/temp/update/void";


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
