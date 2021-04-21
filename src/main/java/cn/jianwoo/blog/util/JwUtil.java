package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    /**
     *
     * 计算字符串字节大小为long类型<br>
     *     eg:1024MB==>1073741824
     *
     * @author gulihua
     * @param maxSize
     * @return
     */
    public static Long processMaxSizeStr(String maxSize) {
        if (StringUtils.isBlank(maxSize))
            return 0L;
        int i = 0;
        long baseBit = 1;
        long size = 0;
        boolean bitFlag = false;

        while (i < maxSize.length()) {
            char d = maxSize.charAt(i);

            if (d >= '0' && d <= '9') {
                size = size * 10 + Integer.parseInt(String.valueOf(d));
            } else {
                switch (d) {
                    case 'b':
                        bitFlag = true;
                        break;
                    case 'B':
                        bitFlag = false;
                        break;
                    case 'k':
                    case 'K':
                        baseBit = 1024;
                        break;
                    case 'm':
                    case 'M':
                        baseBit = 1024 * 1024;
                        break;
                    case 'g':
                    case 'G':
                        baseBit = 1024 * 1024 * 1024;
                        break;
                    case 't':
                    case 'T':
                        baseBit = 1024 * 1024 * 1024 * 1024;
                        break;
                    case 'p':
                    case 'P':
                        baseBit = 1024 * 1024 * 1024 * 1024 * 1024;
                        break;
                    default:
                        baseBit = 1;
                }
            }
            i++;

        }
        return bitFlag ? baseBit * size / 8 : baseBit * size;
    }

    /**
     *
     * 解析long字节为字符串<br>
     *  eg:1073741824==>1.00GB
     * @author gulihua
     * @param %param name% %param description%
     * @return
     */
    public static String parseSize(long size) {
        String[] unit = {"B", "KB", "MB", "GB", "TB", "PB"};
        long s;
        int i = 0;
        while (( s = size / 1024) >= 1 && i < 6){
            size=s;
            i++;
        }
        BigDecimal sizeStr = new BigDecimal(String.valueOf(size));
        return sizeStr.setScale(2, RoundingMode.HALF_UP).toString().concat(unit[i]);
    }

}
