package cn.jianwoo.blog.config.router;

import cn.jianwoo.blog.constants.Constants;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-08 17:07
 */
public class UrlConfig {
    public static final int DEFAULT_VERSION = 1;
    public static final String VERSION_PLACEHOLDER = "/{version}";
    private static final String VERSION_PRE_FIX = "v";

    protected static String getVersion(float version) {
        if (version <= 0) {
            version = 1;
        }
        //...?v=1
        return Constants.URL_PARAM_PREFIX + VERSION_PRE_FIX + Constants.SYMBOL_EQUALS + version;
    }

    /**
     * 获取默认的url
     *
     * @param prefix url前缀上下文
     * @param url    url
     * @return
     * @author gulihua
     */
    public static String getUrl(String prefix, String url) {
        return getUrl(prefix, url, DEFAULT_VERSION);
    }

    /**
     * 获取指定版本的url
     *
     * @param prefix  url前缀上下文
     * @param url     url
     * @param version 版本
     * @return
     * @author gulihua
     */
    public static String getUrl(String prefix, String url, int version) {
        return prefix + url + getVersion(version);
    }

}
