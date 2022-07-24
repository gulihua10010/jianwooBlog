package cn.jianwoo.blog.constants;

/**
 * @author GuLihua
 * @Description
 * @date 2020-11-25 15:09
 */
public final class WebConfDataConfig {

    /**
     * 站点标题
     */
    public static final String TITLE = "TITLE";

    /**
     * 网站作者
     */
    public static final String AUTHOR = "AUTHOR";

    /**
     * 网站关键字
     */
    public static final String KEYWORDS = "KEYWORDS";

    /**
     * 网站描述
     */
    public static final String DESCRIPTION = "DESCRIPTION";

    /**
     * 网站备案
     */
    public static final String RECORD = "RECORD";

    /**
     * 网站域名
     */
    public static final String DOMAIN = "DOMAIN";

    /**
     * 网站底部信息(支持html)
     */
    public static final String FOOT_HTML = "FOOT_HTML";

    /**
     * 上传logo
     */
    public static final String LOGO = "LOGO";

    /**
     * 首页顶部图片:（建议9：1图片）
     */
    public static final String TOP_IMG = "TOP_IMG";

    /**
     * 每页显示最大文章数量
     */
    public static final String NUM_PER_PAGE = "NUM_PER_PAGE";

    /**
     * 开启评论
     */
    public static final String IS_COMMENT = "IS_COMMENT";

    /**
     * 登录是否需要验证码
     */
    public static final String IS_LOGIN_NEED_CAPTCHA = "IS_LOGIN_NEED_CAPTCHA";

    /**
     * 忘记密码是否需要图形验证码
     */
    public static final String IS_FORGET_NEED_CAPTCHA = "IS_FORGET_NEED_CAPTCHA";

    /**
     * 邮件服务器的SMTP地址
     */
    public static final String EMAIL_HOST = "EMAIL_HOST";

    /**
     * 邮件服务器的SMTP端口
     */
    public static final String EMAIL_PORT = "EMAIL_PORT";

    /**
     * 发件人（必须正确，否则发送失败）
     */
    public static final String EMAIL_SENDER = "EMAIL_SENDER";

    /**
     * 用户名（注意：如果使用foxmail邮箱，此处user为qq号）
     */
    public static final String EMAIL_USER = "EMAIL_USER";

    /**
     * 邮件收件人昵称
     */
    public static final String EMAIL_SENDER_NICK = "EMAIL_SENDER_NICK";

    /**
     * 密码（注意，某些邮箱需要为SMTP服务单独设置密码，详情查看相关帮助）
     */
    public static final String EMAIL_PWD = "EMAIL_PWD";

    /**
     * 使用 STARTTLS安全连接，STARTTLS是对纯文本通信协议的扩展。
     */
    public static final String EMAIL_STARTTLS = "EMAIL_STARTTLS";

    /**
     * 使用SSL安全连接
     */
    public static final String EMAIL_SSL = "EMAIL_SSL";

    /**
     * 指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字
     */
    public static final String EMAIL_SOCKET_FACTORY_CLASS = "EMAIL_SOCKET_FACTORY_CLASS";

    /**
     * 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类, 默认值为true
     */
    public static final String EMAIL_SOCKET_FACTORY_FALLBACK = "EMAIL_SOCKET_FACTORY_FALLBACK";

    /**
     * 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口456
     */
    public static final String EMAIL_SOCKET_FACTORY_PORT = "EMAIL_SOCKET_FACTORY_PORT";

    /**
     * SMTP超时时长，单位毫秒，缺省值不超时
     */
    public static final String EMAIL_STMT_TIMEOUT = "EMAIL_STMT_TIMEOUT";

    /**
     * Socket连接超时值，单位毫秒，缺省值不超时
     */
    public static final String EMAIL_CONNECTION_TIMEOUT = "EMAIL_CONNECTION_TIMEOUT";

    /**
     * 登录最大失败次数, 如果登录失败达到允许的最大失败次数,则冻结1小时
     */
    public static final String MAX_LOGIN_FAILED_TIMES = "MAX_LOGIN_FAILED_TIMES";

    /**
     * 评论频率表达式, 数字1:次数,*代表不限<br> 数字2:时间<br>后面是时间单位,支持['Second(s)','Minutes(s)','Hour(s)']
     */
    public static final String COMMENT_ON_FREQUENCY = "COMMENT_ON_FREQUENCY";

    /**
     * IP访问流量控制, 同一个IP每秒最多多少次请求
     */
    public static final String IP_ACCESS_TRAFFIC_CTRL = "IP_ACCESS_TRAFFIC_CTRL";


    /**
     * 博客是否开启评论(全局), 如果关闭,所有文章将不能被评论
     */
    public static final String GLOBAL_COMMENT_ALLOW = "GLOBAL_COMMENT_ALLOW";


    /**
     * 上传至CDN, 上传至七牛云CDN
     */
    public static final String IS_UPLOAD_TO_QINIU_CDN = "IS_UPLOAD_TO_QINIU_CDN";

    /**
     * 异常邮件通知, 发送给管理员的邮箱
     */
    public static final String EXCEPTION_EMAIL_NOTIFY = "EXCEPTION_EMAIL_NOTIFY";

    /**
     * 一天内同一IP最多可以发多少条评论, -1为不限制
     */
    public static final String MAX_COMMENTS_ONE_DAY_ONE_IP = "MAX_COMMENTS_ONE_DAY_ONE_IP";

}
