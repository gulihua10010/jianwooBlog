package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-22 9:52
 */
@Slf4j
public class JwUtil {

    public JwUtil() {
    }

    public static Long generateArticleOid() {
        Long currMullis = Calendar.getInstance().getTimeInMillis();
        String str = currMullis.toString().substring(currMullis.toString().length() - 8
        );
        Integer r = TestUtil.getInstance().getRandomInt(0, 10000);
        if (r < 10) {
            str += ("000" + r.toString());
        } else if (r < 100) {
            str += ("00" + r.toString());
        } else if (r < 1000) {
            str += ("0" + r.toString());
        } else {
            str += (r.toString());
        }
        return Long.valueOf(str);
    }

    /**
     * 生成不含-的随机UUID
     *
     * @return 不含-的随机UUID
     * @author gulihua
     */
    public static String randomUUIDWithoutDash() {
        return java.util.UUID.randomUUID().toString().replaceAll("-", "").trim();
    }

    /**
     * 把html标签文本的标签移除(不含&lt;img&gt;&lt;video&gt;&lt;source&gt;标签)<br/>
     *
     * @param html html标签文本
     * @return 纯html文本
     * @author gulihua
     */
    public static String clearHtmlWithoutMedia(String html) {
        return html.replaceAll(Constants.CLEAR_HTML_TAGS_WITHOUT_MEDIA_REGEX, Constants.BLANK).
                replaceAll(Constants.NEW_LINE_REGEX, Constants.BLANK).trim();
    }

    /**
     * 把html标签文本的标签移除<br/>
     *
     * @param html html标签文本
     * @return 纯html文本
     * @author gulihua
     */
    public static String clearHtml(String html) {
        return html.replaceAll(Constants.CLEAR_HTML_TAGS_REGEX, Constants.BLANK).
                replaceAll(Constants.NEW_LINE_REGEX, Constants.BLANK).trim();
    }

}
