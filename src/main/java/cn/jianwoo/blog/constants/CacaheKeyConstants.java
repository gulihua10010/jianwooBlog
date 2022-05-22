package cn.jianwoo.blog.constants;

/**
 * @author GuLihua
 * @Description Cache键
 * @date 2021-06-24 15:29
 */
public final class CacaheKeyConstants {

    /**
     * 缓存admin键前缀
     */
    public final static String KEY_ADMIN_PREFIX = "jianwoo:admin";


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

    public static final String LOGIN_CAPTCHA_AUTH = "jianwoo:admin:LOGIN.CAPTCHA.AUTH:{0}";
    public static final String FORGET_CAPTCHA_AUTH = "jianwoo:admin:FORGET.CAPTCHA.AUTH:{0}";
    //gt_server_status
    public static final String GT_SERVER_STATUS = "jianwoo:admin:GT.SERVER.STATUS:{0}";
    public static final String GT_SERVER_USER = "jianwoo:admin:GT.SERVER.USER:{0}";

    /**
     * 网站配置
     */
    public final static String WEBCONF_KEY = "jianwoo:web:config:{0}";
    /**
     * 网站配置类型
     */
    public final static String WEBCONF_TYPE = "jianwoo:web:config:type:{0}";
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

    /**
     * ADMIN oid -- name
     */
    public final static String ADMIN_OID_NAME_KEY = "jianwoo:admin:oid::name:{0}";
    /**
     * LoginID -- VerifyCode
     */
    public final static String VERIFY_CODE_LOGIN_ID = "jianwoo:admin:verify:code::loginID:{0}";


    /**
     * 后台菜单
     */
    public final static String ADMIN_MENU_KEY = "jianwoo:admin:menu";

    /**
     * IP黑名单
     */
    public final static String IP_BLACK_KEY = "jianwoo:ip:black::{0}";
    /**
     * IP黑名单标识符
     */
    public final static String IP_BLACK_FLAG_KEY = "jianwoo:ip:black:flag";

    /**
     * IP限流控制
     */
    public final static String IIP_ACCESS_TRAFFIC_CTRL_KEY = "jianwoo:ip:access:traffic:ctrl::{0}";

    /**
     * IP黑名单预警
     */
    public final static String IP_BLACK_WARN_KEY = "jianwoo:ip:black:warn::{0}";


}
