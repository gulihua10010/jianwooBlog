package cn.jianwoo.blog.util;


import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.StatusCode;
import cn.jianwoo.blog.dto.response.GeetestResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class GeetestLibUtil {

    private final String verName = "4.0";
    private final String sdkLang = "java";

    private final String apiUrl = "http://api.geetest.com";

    private final String registerUrl = "/register.php";
    private final String validateUrl = "/validate.php";

    private final String json_format = "1";

    /**
     * 极验验证二次验证表单数据 chllenge
     */
    public static final String fn_geetest_challenge = "geetest_challenge";

    /**
     * 极验验证二次验证表单数据 validate
     */
    public static final String fn_geetest_validate = "geetest_validate";

    /**
     * 极验验证二次验证表单数据 seccode
     */
    public static final String fn_geetest_seccode = "geetest_seccode";

    /**
     * 公钥
     */
    private String captchaId = "";

    /**
     * 私钥
     */
    private String privateKey = "";

    /**
     * 是否开启新的failback
     */
    private boolean newFailback = false;

    /**
     * 返回字符串
     */
    private GeetestResponse response;

    /**
     * 调试开关，是否输出调试日志
     */
    public boolean debugCode = true;

    /**
     * 极验验证API服务状态Session Key
     */
    public String gtServerStatusSessionKey = "gt_server_status";

    /**
     * 带参数构造函数
     *
     * @param captchaId
     * @param privateKey
     */
    public GeetestLibUtil(String captchaId, String privateKey, boolean newFailback) {

        this.captchaId = captchaId;
        this.privateKey = privateKey;
        this.newFailback = newFailback;
    }

    /**
     * 获取本次验证初始化返回字符串
     *
     * @return 初始化结果
     */
    public GeetestResponse getResponse() {

        return response;

    }

    public String getVersionInfo() {

        return verName;

    }

    /**
     * 预处理失败后的返回格式串
     *
     * @return
     */
    private GeetestResponse getFailPreProcessRes() {

        Long rnd1 = Math.round(Math.random() * 100);
        Long rnd2 = Math.round(Math.random() * 100);
        String md5Str1 = md5Encode(rnd1 + "");
        String md5Str2 = md5Encode(rnd2 + "");
        String challenge = md5Str1 + md5Str2.substring(0, 2);
        GeetestResponse response = new GeetestResponse();
        response.setChallenge(challenge);
        response.setGt(this.captchaId);
        response.setStatus(StatusCode.FAILED.getStatus());
        response.setNewCaptcha(this.newFailback);
        return response;


    }

    /**
     * 预处理成功后的标准串
     */
    private GeetestResponse getSuccessPreProcessRes(String challenge) {
        log.info("===>> Geetest: challenge: {}", challenge);
        GeetestResponse response = new GeetestResponse();
        response.setChallenge(challenge);
        response.setGt(this.captchaId);
        response.setStatus(StatusCode.SUCCESS.getStatus());
        return response;

    }

    /**
     * 验证初始化预处理
     *
     * @return 1表示初始化成功，0表示初始化失败
     */
    public boolean preProcess(HashMap<String, String> data) {

        if (!registerChallenge(data)) {
            this.response = this.getFailPreProcessRes();
            return false;
        }
        return true;

    }

    /**
     * 用captchaID进行注册，更新challenge
     *
     * @return true 表示注册成功， false表示注册失败
     */
    private boolean registerChallenge(HashMap<String, String> data) {

        try {
            String userId = data.get("user_id");
            String clientType = data.get("client_type");
            String ipAddress = data.get("ip_address");

            String getUrl = apiUrl + registerUrl + "?";
            String param = "gt=" + this.captchaId + "&json_format=" + this.json_format;

            if (userId != null) {
                param = param + "&user_id=" + userId;
            }
            if (clientType != null) {
                param = param + "&client_type=" + clientType;
            }
            if (ipAddress != null) {
                param = param + "&ip_address=" + ipAddress;
            }
            log.info("===>> Geetest: GET_URL:{}", getUrl + param);
            BaseResponseDto responseDto = HttpClientUtil.doGet(getUrl + param);
            if (!responseDto.isSuccess()) {
                log.info("===>> Geetest: gtServer register challenge failed, response: {}", responseDto.getMsg());
                return false;

            }

            log.info("===>> Geetest: result: {}", responseDto.getMsg());
            GeetestResponse response = JSONObject.parseObject(responseDto.getMsg(), GeetestResponse.class);
            String challenge = response.getChallenge();
            if (challenge.length() == 32) {
                this.response = this.getSuccessPreProcessRes(this.md5Encode(challenge + this.privateKey));
                return true;
            } else {
                log.info("===>> Geetest: gtServer register challenge error");
                return false;
            }
        } catch (Exception e) {
            log.info("===>> Geetest exec failed, e:", e);
        }
        return false;
    }

    /**
     * 判断一个表单对象值是否为空
     *
     * @param gtObj
     * @return
     */
    protected boolean objIsEmpty(Object gtObj) {

        if (gtObj == null) {

            return true;

        }

        if (gtObj.toString().trim().length() == 0) {

            return true;

        }

        return false;
    }

    /**
     * 检查客户端的请求是否合法,三个只要有一个为空，则判断不合法
     *
     * @param challenge challenge
     * @param validate  validate
     * @param secCode   secCode
     * @return
     */
    private boolean resquestIsLegal(String challenge, String validate, String secCode) {

        if (objIsEmpty(challenge)) {

            return false;

        }

        if (objIsEmpty(validate)) {

            return false;

        }

        if (objIsEmpty(secCode)) {

            return false;

        }

        return true;
    }


    /**
     * 服务正常的情况下使用的验证方式,向gt-server进行二次验证,获取验证结果
     *
     * @param challenge
     * @param validate
     * @param secCode
     * @return 验证结果, 1表示验证成功0表示验证失败
     */
    public boolean enhencedValidateRequest(String challenge, String validate, String secCode, HashMap<String, String> data) {

        if (!resquestIsLegal(challenge, validate, secCode)) {
            return false;
        }
        log.info("===>> Geetest: request legitimate");

        String userId = data.get("user_id");
        String clientType = data.get("client_type");
        String ipAddress = data.get("ip_address");

        String postUrl = this.apiUrl + this.validateUrl;
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("challenge", challenge);
        paramMap.put("validate", validate);
        paramMap.put("seccode", secCode);
        paramMap.put("json_format", this.json_format);
        if (userId != null) {
            paramMap.put("user_id", userId);
        }
        if (clientType != null) {
            paramMap.put("client_type", clientType);
        }
        if (ipAddress != null) {
            paramMap.put("ip_address", ipAddress);
        }

        log.info("===>> Geetest: param: {}", paramMap);


        BaseResponseDto responseDto = BaseResponseDto.SUCCESS;
        try {

            if (validate.length() <= 0) {
                return false;
            }
            if (!checkResultByPrivate(challenge, validate)) {
                return false;
            }
            responseDto = HttpClientUtil.doPost(postUrl, paramMap);
            log.info("===>> Geetest: response: {}", responseDto);

        } catch (Exception e) {
            log.error("===>>GeetestLibUtil.enhencedValidateRequest exec failed", e);
        }

        String returnSecCode = "";
        if (StringUtils.isBlank(responseDto.getMsg()) || Constants.FALSE.equalsIgnoreCase(responseDto.getMsg())) {
            return false;
        }

        JSONObject returnMap = JSON.parseObject(responseDto.getMsg());
        returnSecCode = returnMap.getString("seccode");
        if (returnSecCode.equals(md5Encode(secCode))) {
            return true;
        } else {
            return false;

        }


    }


    /**
     * failback使用的验证方式
     *
     * @param challenge
     * @param validate
     * @param secCode
     * @return 验证结果, true表示验证成功, false表示验证失败
     */
    public boolean failbackValidateRequest(String challenge, String validate, String secCode) {

        if (!resquestIsLegal(challenge, validate, secCode)) {
            return false;
        }

        return true;
    }


    protected boolean checkResultByPrivate(String challenge, String validate) {
        String encodeStr = md5Encode(privateKey + "geetest" + challenge);
        return validate.equals(encodeStr);
    }


    /**
     * md5 加密
     *
     * @param plainText
     * @return
     * @time 2014年7月10日 下午3:30:01
     */
    private String md5Encode(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            log.error("===>>GeetestLibUtil.md5Encode exec failed", e);

        }
        return re_md5;
    }

}
