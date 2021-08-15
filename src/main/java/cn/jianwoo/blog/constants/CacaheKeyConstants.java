package cn.jianwoo.blog.constants;

/**
 * @author GuLihua
 * @Description Cache键
 * @date 2021-06-24 15:29
 */
public final class CacaheKeyConstants {

    /**
     * Access token cache.
     */
    public final static String TOKEN_ACCESS_CACHE = "jianwoo:admin:access:token:{0}";

    /**
     * 用户登录状态
     */
    public final static String LOGIN_USER_STATUS = "jianwoo:admin:login:user:status:{0}";


    /**
     * 无效token存放
     */
    public final static String INVALID_TOKEN = "jianwoo:admin:invalid:token:{0}";

    public static final String LOGIN_CAPTCHA_AUTH = "JIANWOO.LOGIN.CAPTCHA.AUTH";

    /**
     * 网站配置
     */
    public final static String WEBCONF_KEY = "jianwoo:web:config:{0}";
    /**
     * 邮件配置
     */
    public final static String EMAIL_CFG = "jianwoo:web:email:config";

    /**
     * ADMIN主键
     */
    public final static String ADMIN_OID_KEY = "jianwoo:admin:oid:{0}";

    /**
     * ADMIN name
     */
    public final static String ADMIN_NAME_KEY = "jianwoo:admin:name:{0}";


}
