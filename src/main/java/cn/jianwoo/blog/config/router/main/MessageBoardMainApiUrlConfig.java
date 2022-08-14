package cn.jianwoo.blog.config.router.main;

import cn.jianwoo.blog.config.router.UrlConfig;

/**
 * @author gulihua
 * @Description
 * @date 2022-03-10 18:03
 */
public class MessageBoardMainApiUrlConfig extends UrlConfig {
    public static final String URL_PREFIX = "/api/message/board";
    public static final String URL_MESSAGE_BOARD_QUERY_PAGE_LIST = "/query/page/list";
    public static final String URL_MESSAGE_BOARD_CREATE = "/create";
    public static final String URL_MESSAGE_BOARD_UPDATE = "/update";
    public static final String URL_MESSAGE_BOARD_PRAISE_ADD = "/praise/add";
    public static final String URL_MESSAGE_BOARD_REMOVE = "/remove";

}
