package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:03
 */
public class CommentMainApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/comment";
    public static final String URL_QUERY_PAGE_ARTICLE_LIST = "/query/page/article/list";
    public static final String URL_CREATE_COMMENT = "/create";
    public static final String URL_UPDATE_COMMENT = "/update";
    public static final String URL_COMMENT_PRAISE_ADD = "/praise/add";
    public static final String URL_REMOVE_COMMENT = "/remove";

}
