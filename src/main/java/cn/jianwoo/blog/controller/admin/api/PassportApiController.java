package cn.jianwoo.blog.controller.admin.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.GeetestConfig;
import cn.jianwoo.blog.config.apiversion.ApiVersion;
import cn.jianwoo.blog.config.router.admin.PassportApiUrlConfig;
import cn.jianwoo.blog.constants.CacaheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dto.request.EmailSendRequest;
import cn.jianwoo.blog.dto.request.ForgetPasswordRequest;
import cn.jianwoo.blog.dto.request.UserRequest;
import cn.jianwoo.blog.dto.response.CaptchaTokenResponse;
import cn.jianwoo.blog.dto.response.ForgetPwdResponse;
import cn.jianwoo.blog.dto.response.GeetestResponse;
import cn.jianwoo.blog.enums.GtCaptchaEnum;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.ForgetPwdResBO;
import cn.jianwoo.blog.util.GeetestLibUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 */
@RestController
@RequestMapping(PassportApiUrlConfig.URL_PREFIX)
public class PassportApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(PassportApiController.class);
    @Autowired
    private AdminBizService adminBizService;
    @Autowired
    private CacheStore<String, String> cacheStore;
    @Autowired
    private WebconfBizService webconfBizService;

    public static final String LOGIN_SESSION = "JIANWOO.LOGIN.SESSION";


    /**
     * 初始化登录验证码<br/>
     * url:/api/passport/captcha/init<br/>
     * <p>
     *
     * @return 返回响应 {@link BaseResponseDto}
     * challenge
     * gt
     * newCaptcha
     * success
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(PassportApiUrlConfig.URL_PASSPORT_CAPTCHA_INIT)
    public String startCaptcha() {
        GeetestLibUtil gtSdk = new GeetestLibUtil(GeetestConfig.getGeetestId(), GeetestConfig.getGeetestKey(),
                GeetestConfig.getNewFailBack());


        String userid = "jianwoo";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP
        String guid = request.getParameter(Constants.GUID);

        //进行验证预处理
        boolean isGtServiceSucc = gtSdk.preProcess(param);

//        //将服务器状态设置到session中
//        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, isGtServiceSucc);
//        //将userid设置到session中
//        request.getSession().setAttribute("userid", userid);
        String statusKey = MessageFormat.format(CacaheKeyConstants.GT_SERVER_STATUS, guid);
        String userKey = MessageFormat.format(CacaheKeyConstants.GT_SERVER_USER, guid);
        cacheStore.put(statusKey, isGtServiceSucc ? Constants.YES : Constants.NO);
        cacheStore.put(userKey, userKey);
        GeetestResponse resStr = gtSdk.getResponse();
        logger.info("==>> LoginController.startCaptcha, call sdk res :{}", resStr);
        return super.responseToJSONString(resStr);

    }

    /**
     * 验证登录验证码<br/>
     * url:/api/passport/captcha/verify<br/>
     * <p>
     * param
     * geetest_challenge<br/>
     * geetest_validate<br/>
     * geetest_seccode<br/>
     *
     * @return 返回响应 {@link BaseResponseDto}
     * token
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(PassportApiUrlConfig.URL_PASSPORT_CAPTCHA_VERIFY)
    public String verifyLogin() {
        GeetestLibUtil gtSdk = new GeetestLibUtil(GeetestConfig.getGeetestId(), GeetestConfig.getGeetestKey(),
                GeetestConfig.getNewFailBack());

        String challenge = request.getParameter(GeetestLibUtil.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLibUtil.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLibUtil.fn_geetest_seccode);
        String type = request.getParameter(GeetestLibUtil.fn_geetest_type);
        String guid = request.getParameter(Constants.GUID);

        //从session中获取gt-server状态
//        Boolean isGtServiceSucc = (Boolean) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        String statusKey = MessageFormat.format(CacaheKeyConstants.GT_SERVER_STATUS, guid);
        String userKey = MessageFormat.format(CacaheKeyConstants.GT_SERVER_USER, guid);
        String isGtServiceSucc = cacheStore.get(statusKey).orElse(null);
        String userid = cacheStore.get(userKey).orElse(null);

        if (StringUtils.isBlank(isGtServiceSucc)) {
            isGtServiceSucc = Constants.NO;
        }

        //从session中获取userid
//        String userid = (String) request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        boolean gtResult = false;

        if (Constants.YES.equals(isGtServiceSucc)) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);

        } else {
            // gt-server非正常情况下，进行failback模式验证
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }


        if (gtResult) {
            // 验证成功
            String token = JwUtil.randomUUIDWithoutDash();
            String cacheKey = MessageFormat.format(GtCaptchaEnum.getKey(type), guid);
            cacheStore.put(cacheKey, token, 10, TimeUnit.MINUTES);
//            request.getSession().setAttribute(LOGIN_CAPTCHA_AUTH, token);
            return super.responseToJSONString(new CaptchaTokenResponse(token));
        } else {
            return super.responseToJSONString(BaseResponseDto.SYSTEM_ERROR);


        }

    }

    /**
     * 用户授权登录<br/>
     * url:/api/passport/login/auth<br/>
     *
     * @param param JSON 参数({@link UserRequest})
     *              username<br/>
     *              password<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * token
     * @author gulihua
     */
    @Deprecated
    @PostMapping(PassportApiUrlConfig.URL_PASSPORT_LOGIN_AUTH)
    public String loginAuth(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            logger.info("====do login====");
            UserRequest requestParam = this.convertParam(param, UserRequest.class);
            BizValidation.paramValidate(requestParam.getUsername(), "username", "用户名不能为空!");
            BizValidation.paramValidate(requestParam.getPassword(), "password", "密码不能为空!");
            String isNeed = webconfBizService.queryWebconfByKey(WebConfDataConfig.IS_LOGIN_NEED_CAPTCHA);

            if (Constants.TRUE.equals(isNeed)) {
                String tokenStore = cacheStore.get(CacaheKeyConstants.LOGIN_CAPTCHA_AUTH).orElse(null);
//                String tokenSession = (String) request.getSession().getAttribute(LOGIN_CAPTCHA_AUTH);
                String tokenSession = requestParam.getAccess_token();
                boolean isCaptcha = null != tokenSession && tokenSession.equals(tokenStore);
                if (!isCaptcha) {
                    throw new JwBlogException(ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID,
                            ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID_DESC);
                }
            }


            AuthToken authToken = adminBizService.authLogin(requestParam.getUsername(), requestParam.getClientIp());
            if (Constants.TRUE.equals(isNeed)) {
                cacheStore.delete(CacaheKeyConstants.LOGIN_CAPTCHA_AUTH);
                request.getSession().removeAttribute(CacaheKeyConstants.LOGIN_CAPTCHA_AUTH);
            }

            request.getSession().setAttribute(LOGIN_SESSION, authToken.getAccessToken());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 邮箱验证码发送<br/>
     * url:/api/passport/forget/email/captcha/send<br/>
     *
     * @param param JSON 参数({@link EmailSendRequest})<br/>
     *              loginId<br/>
     *              email<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * token
     * @author gulihua
     */
    @PostMapping(PassportApiUrlConfig.URL_PASSPORT_FORGET_EMAIL_CAPTCHA_SEND)
    public String doSendEmailCaptcha4Forget(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            logger.info(">>doSendEmailCaptcha4Forget...");
            EmailSendRequest requestParam = this.convertParam(param, EmailSendRequest.class);
            BizValidation.paramValidate(requestParam.getLoginId(), "loginId", "登录名不能为空!");
            BizValidation.paramValidate(requestParam.getEmail(), "email", "邮箱地址不能为空!");
            adminBizService.doSendCaptcha4Forget(requestParam.getLoginId(), requestParam.getEmail());

        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }


    /**
     * 忘记密码<br/>
     * url:/api/passport/forget/captcha/auth<br/>
     *
     * @param param JSON 参数({@link ForgetPasswordRequest})<br/>
     *              loginID<br/>
     *              captchaCode<br/>
     * @return 返回响应 {@link ForgetPwdResponse}
     * flagSuccess<br/>
     * reason<br/>
     * loginIdEncrypt<br/>
     * captchaCodeEncrypt<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(PassportApiUrlConfig.URL_PASSPORT_FORGET_CAPTCHA_AUTH)
    public String doForgetCaptchaAuth(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ForgetPasswordRequest request = this.convertParam(param, ForgetPasswordRequest.class);
            BizValidation.paramValidate(request.getLoginID(), "loginID", "登录ID不能为空!");
            BizValidation.paramValidate(request.getCaptchaCode(), "captchaCode", "验证码不能为空!");
            BizValidation.paramValidate(request.getEmail(), "email", "邮箱地址不能为空!");
            String isNeed = webconfBizService.queryWebconfByKey(WebConfDataConfig.IS_FORGET_NEED_CAPTCHA);
            if (Constants.TRUE.equals(isNeed)) {
                String accessToken = request.getCaptcha_token();
                String guid = request.getGuid();
                String cacheKey = MessageFormat.format(CacaheKeyConstants.FORGET_CAPTCHA_AUTH, guid);
                String tokenStore = cacheStore.get(cacheKey).orElse(null);
                boolean isCaptcha = null != accessToken && accessToken.equals(tokenStore);
                if (!isCaptcha) {
                    throw new JwBlogException(ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID,
                            ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID_DESC);
                }
            }
            ForgetPwdResBO resBO = adminBizService.doForgetCaptchaAuth(request.getLoginID(), request.getEmail(), request.getCaptchaCode());
            ForgetPwdResponse response = ForgetPwdResponse.getInstance();
            BeanUtils.copyProperties(resBO, response);
            if (!resBO.isFlagSuccess()) {
                response.setMsg(resBO.getReason());
                response.setStatus(resBO.getCode());
            }

            return super.responseToJSONString(response);
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
    }


    /**
     * 忘记密码<br/>
     * url:/api/passport/forget/password/modify<br/>
     *
     * @param param JSON 参数({@link ForgetPasswordRequest})<br/>
     *              loginIdEncrypt<br/>
     *              newPasswordEncrypt<br/>
     *              captchaCodeEncrypt<br/>
     *              captcha_token<br/>
     * @return 返回响应 {@link BaseResponseDto}
     * status<br/>
     * @author gulihua
     */
    @ApiVersion()
    @PostMapping(PassportApiUrlConfig.URL_USER_FORGET_PASSWORD_MODIFY)
    public String doForgetPasswordModify(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            ForgetPasswordRequest request = this.convertParam(param, ForgetPasswordRequest.class);
            BizValidation.paramValidate(request.getLoginIdEncrypt(), "loginIdEncrypt", "登录ID不能为空!");
            BizValidation.paramValidate(request.getCaptchaCodeEncrypt(), "captchaCodeEncrypt", "验证码不能为空!");
            BizValidation.paramValidate(request.getNewPasswordEncrypt(), "newPasswordEncrypt", "新密码不能为空!");


            adminBizService.doChangePassword4Forget(request.getLoginIdEncrypt(), request.getNewPasswordEncrypt(),
                    request.getCaptchaCodeEncrypt());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);
    }


}
