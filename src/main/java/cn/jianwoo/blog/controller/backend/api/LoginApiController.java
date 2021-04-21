package cn.jianwoo.blog.controller.backend.api;

import cn.jianwoo.blog.base.BaseController;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.config.GeetestConfig;
import cn.jianwoo.blog.config.router.LoginApiUrlConfig;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.dto.request.UserRequest;
import cn.jianwoo.blog.dto.response.GeetestResponse;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.service.biz.WebconfBizService;
import cn.jianwoo.blog.service.bo.WebconfBO;
import cn.jianwoo.blog.util.GeetestLibUtil;
import cn.jianwoo.blog.util.JwUtil;
import cn.jianwoo.blog.validation.BizValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author gulihua
 */
@RestController
@RequestMapping(LoginApiUrlConfig.URL_PREFIX)
public class LoginApiController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(LoginApiController.class);
    @Autowired
    private AdminBizService adminBizService;
    @Autowired
    private CacheStore<String, String> cacheStore;
    @Autowired
    private WebconfBizService webconfBizService;

    public static final String LOGIN_CAPTCHA_AUTH = "JIANWOO.LOGIN.CAPTCHA.AUTH";
    public static final String LOGIN_SESSION = "JIANWOO.LOGIN.SESSION";

    @GetMapping(LoginApiUrlConfig.URL_LOGIN_CAPTCHA_INIT)
    public String startCaptcha() {
        GeetestLibUtil gtSdk = new GeetestLibUtil(GeetestConfig.getGeetestId(), GeetestConfig.getGeetestKey(),
                GeetestConfig.getNewFailBack());


        String userid = "jianwoo";

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        //进行验证预处理
        boolean isGtServiceSucc = gtSdk.preProcess(param);

        //将服务器状态设置到session中
        request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, isGtServiceSucc);
        //将userid设置到session中
        request.getSession().setAttribute("userid", userid);

        GeetestResponse resStr = gtSdk.getResponse();
        logger.info("==>> LoginController.startCaptcha, call sdk res :{}", resStr);
        return super.responseToJSONString(resStr);

    }

    @PostMapping(LoginApiUrlConfig.URL_LOGIN_CAPTCHA_VERIFY)
    public String VerifyLogin() {
        GeetestLibUtil gtSdk = new GeetestLibUtil(GeetestConfig.getGeetestId(), GeetestConfig.getGeetestKey(),
                GeetestConfig.getNewFailBack());

        String challenge = request.getParameter(GeetestLibUtil.fn_geetest_challenge);
        String validate = request.getParameter(GeetestLibUtil.fn_geetest_validate);
        String seccode = request.getParameter(GeetestLibUtil.fn_geetest_seccode);

        //从session中获取gt-server状态
        Boolean isGtServiceSucc = (Boolean) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
        if (isGtServiceSucc == null) {
            isGtServiceSucc = false;
        }

        //从session中获取userid
        String userid = (String) request.getSession().getAttribute("userid");

        //自定义参数,可选择添加
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("user_id", userid); //网站用户id
        param.put("client_type", "web"); //web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
        param.put("ip_address", "127.0.0.1"); //传输用户请求验证时所携带的IP

        boolean gtResult = false;

        if (isGtServiceSucc) {
            //gt-server正常，向gt-server进行二次验证

            gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);

        } else {
            // gt-server非正常情况下，进行failback模式验证
            gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
        }


        if (gtResult) {
            // 验证成功
            String token = JwUtil.randomUUIDWithoutDash();
            cacheStore.put(LOGIN_CAPTCHA_AUTH, token);
            request.getSession().setAttribute(LOGIN_CAPTCHA_AUTH, token);
            return super.responseToJSONString(BaseResponseDto.SUCCESS);
        } else {
            return super.responseToJSONString(BaseResponseDto.SYSTEM_ERROR);


        }

    }

    @PostMapping(LoginApiUrlConfig.URL_LOGIN_AUTH)
    public String loginAuth(@RequestBody String param) {
        try {
            super.printRequestParams(param);
            UserRequest requestParam = this.convertParam(param, UserRequest.class);
            BizValidation.paramValidate(requestParam.getUsername(), "username", "用户名不能为空!");
            BizValidation.paramValidate(requestParam.getPassword(), "password", "密码不能为空!");
//            WebconfBO webconf = webconfBizService.queryConfigWithBO();
//            Boolean isCaptchaOn = webconf.getIsCaptchaOn();
//            if (isCaptchaOn) {
//                String tokenStore = cacheStore.get(LOGIN_CAPTCHA_AUTH).orElse(null);
//                String tokenSession = (String) request.getSession().getAttribute(LOGIN_CAPTCHA_AUTH);
//                boolean isCaptcha = null != tokenSession && tokenSession.equals(tokenStore);
//                if (!isCaptcha) {
//                    throw new JwBlogException(ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID,
//                            ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID_DESC);
//                }
//            }


            AuthToken authToken = adminBizService.authLogin(requestParam.getUsername(), requestParam.getPassword(), requestParam.getClientIp());
//            if (isCaptchaOn) {
//                cacheStore.delete(LOGIN_CAPTCHA_AUTH);
//                request.getSession().removeAttribute(LOGIN_CAPTCHA_AUTH);
//            }

            request.getSession().setAttribute(LOGIN_SESSION, authToken.getAccessToken());
        } catch (JwBlogException e) {
            return super.exceptionToString(e);
        }
        return super.responseToJSONString(BaseResponseDto.SUCCESS);

    }

}
