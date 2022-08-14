package cn.jianwoo.blog.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import cn.hutool.extra.template.engine.velocity.VelocityEngine;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.exception.EmailTplBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.service.bo.FrequencyBO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-22 9:52
 */
@Slf4j
public class JwUtil {


    private static final ApplicationConfigUtil applicationConfigUtil = SpringUtil.getBean(ApplicationConfigUtil.class);
    public static String base64Security = applicationConfigUtil.getAesSecret();
    public static String avatarSrc = applicationConfigUtil.getUserProfileAvatar();

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
     * 计算字符串字节大小为long类型<br>
     * eg:1024MB==>1073741824
     *
     * @param maxSize
     * @return
     * @author gulihua
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
     * 解析long字节为字符串<br>
     * eg:1073741824==>1.00GB
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    public static String parseSize(long size) {
        String[] unit = {"B", "KB", "MB", "GB", "TB", "PB"};
        long s;
        int i = 0;
        while ((s = size / 1024) >= 1 && i < 6) {
            size = s;
            i++;
        }
        BigDecimal sizeStr = new BigDecimal(String.valueOf(size));
        return sizeStr.setScale(2, RoundingMode.HALF_UP).toString().concat(unit[i]);
    }

    /**
     * 加密字符串<br>
     * 1.对秘钥key解密
     * 2.aes加密<br>
     * 3.base加密<br>
     *
     * @param content 要加密的字符串
     * @return
     * @author gulihua
     */
    public static String encrypt(String content) {

        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES,
                Base64.decode(base64Security));
        //加密
        String encryptHex = aes.encryptHex(content);

        return Base64.encode(encryptHex);

    }

    /**
     * 解密字符串<br>
     * 1.对秘钥key解密
     * 2.base解密<br>
     * 3.aes解密<br>
     *
     * @param encryptHex 要解密的字符串
     * @return
     * @author gulihua
     */
    public static String decrypt(String encryptHex) {
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES,
                Base64.decode(base64Security));
        //解密为字符串
        String decryptStr = aes.decryptStr(new String(Base64.decode(encryptHex)), CharsetUtil.CHARSET_UTF_8);
        return decryptStr;

    }

    public static String generateVerifyCode(int len) {
        String word = "abcdefghijklmnopqrstuvwxyz";
        String wordUpper = word.toUpperCase(Locale.ROOT);
        String num = "0123456789";
        return RandomUtil.randomString(word.concat(wordUpper).concat(num), len);
    }

    /**
     * 解析频率表达式<br>
     * 10 Time(s) 1 Minutes(s)<br>
     * 数字1:次数,*代表不限<br>
     * 数字2:时间<br>
     * 后面是时间单位,支持[\'Second(s)\',\'Minutes(s)\',\'Hour(s)\']
     *
     * @param express 频率表达式
     * @return FrequencyBO
     * @author gulihua
     */
    public static FrequencyBO parseFrequency(String express) {
        if (StringUtils.isBlank(express)) {
            return new FrequencyBO(true);
        }
        String[] exprArr = express.trim().split(" ");

        if (exprArr.length != 4) {
            log.error("The Frequency express [{}] parse failed.", express);
            return new FrequencyBO(true);
        }
        if ("*".equals(exprArr[0].trim())) {
            return new FrequencyBO(true);
        }
        try {
            return new FrequencyBO(exprArr[0], exprArr[2], exprArr[3]);
        } catch (Exception e) {
            log.error("The Frequency express [{}] parse failed.", express);
            log.error("e:\r\n", e);
            return new FrequencyBO(true);
        }


    }

    /**
     * 生成7位随机用户ID
     *
     * @return
     * @author gulihua
     */
    public static String generateUserId() {
        String word = "abcdefghijklmnopqrstuvwxyz";
        String num = "0123456789";
        String prefix = Constants.USER_PREFIX;
        String suffix = RandomUtil.randomString(word.concat(num), 7);
        return prefix.concat(suffix);
    }

    /**
     * 根据ip生成用户名(.替换成_)<br>
     * 例如:127.0.0.1==> 127_0_0_1
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    public static String generateUsername(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        String prefix = Constants.USER_PREFIX;
        String suffix = ip.replace(".", "_");
        return prefix.concat(suffix);
    }

    /***
     * 随机获取用户头像<br>
     * path: src/main/resources/static/comm/img/avatar
     * @return
     * @throws JwBlogException
     */
    public static String fetchUserAvatar() {
        int index = RandomUtil.randomInt(1, 11);
        return avatarSrc + File.separator + index + Constants.FILE_EXT_JPG;
    }


    /**
     * 正则匹配字符串是否是邮箱格式的字符串
     *
     * @param %param name% %param description%
     * @return
     * @author gulihua
     */
    public static boolean isEmail(String email) {
        return ReUtil.isMatch(Constants.EMAIL_REGEX, email);

    }


    public static String doRenderTpl(String content, String param) throws JwBlogException {
        try {
            //自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
            //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
            TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig().setCustomEngine(VelocityEngine.class));

            //假设我们引入的是Beetl引擎，则：
            Template template = engine.getTemplate(content);
            return template.render(JSON.parseObject(param));
        } catch (Exception e) {
            log.error(">>Render Email Template Failed, e:\r\n", e);
            throw EmailTplBizException.TPL_RENDER_FAILED_CN.print();
        }
    }


    public static String format(String v) {
        if (StringUtils.isBlank(v)) {
            return Constants.BLANK;
        }
        return v.trim();
    }

    public static String format(String v, String anther) {
        if (StringUtils.isBlank(v)) {
            return anther;
        }
        return v.trim();
    }

    public static String getRealIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
