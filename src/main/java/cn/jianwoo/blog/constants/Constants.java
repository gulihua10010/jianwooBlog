package cn.jianwoo.blog.constants;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 17:18
 */
public class Constants {
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String TRUE_1 = "1";
    public static final String FALSE_0 = "0";
    public static final String SYSTEM = "SYSTEM";

    public static final String URL_SEPARATOR = "/";
    public static final String BLANK = "";
    public static final String SPACE = " ";
    public static final String COMMA_SEPARATOR = ",";
    public static final String SEMICOLON = ";";
    public static final String FILE_END_LINE = "\r\n";
    public static final String ELLIPSIS = "...";

    public static final String SYMBOL_PERCENT = "%";
    public static final String SYMBOL_AIT = "@";
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String HASH_ALGORITHM = "MD5";

    public static final String SEPARATE_HYPHEN = "_";
    public static final String FILE_POINT = ".";
    //asterisk
    public static final String ASTERISK = "*";
    //Protocol
    public static final String FILE_PROTOCOL = "file:";

    public static final String BLOG_AUTHOR = "博主";
    public static final String UNKNOW = "未知";
    public static final String ANAONYMOUS = "匿名";

    //date format
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_TIMESTAMP = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";

    //validate length
    public static final Integer TITLE_LENGTH = 50;
    public static final Integer AUTHOR_LENGTH = 10;
    public static final Integer TAGS_LENGTH = 10;
    public static final Integer MENU_LENGTH = 10;

    //reg
    public static final String MENU_NAME_REGEX = "^[_#$@\\d\\w]*$";
    public static final String CLEAR_HTML_TAGS = "\\<(?!img |/?video|source ).*?>";


}
