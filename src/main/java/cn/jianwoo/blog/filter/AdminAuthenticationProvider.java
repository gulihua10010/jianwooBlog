package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.constants.WebConfDataConfig;
import cn.jianwoo.blog.dao.base.WebconfTransDao;
import cn.jianwoo.blog.entity.Webconf;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.security.token.AuthToken;
import cn.jianwoo.blog.service.biz.AdminBizService;
import cn.jianwoo.blog.security.token.AuthUserTokenBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.Map;

/**
 * @author GuLihua
 * @Description 自定义认证服务
 * @date 2021-05-25 16:12
 */
@Component
@Slf4j
public class AdminAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AdminBizService adminBizService;
    @Autowired
    private CacheStore<String, String> cacheStore;
    @Autowired
    private WebconfTransDao webconfTransDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("==>AdminAuthenticationProvider.authenticate..");
        JwAuthenticationToken jwAuthenticationToken = (JwAuthenticationToken) authentication;
        // 获取前端表单中输入后返回的用户名、密码
        String userName = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        Map<String, Object> param = jwAuthenticationToken.getParam();
        AuthUserTokenBO user = (AuthUserTokenBO) userDetailsService.loadUserByUsername(userName);
        boolean isValid = passwordEncoder().matches(password, user.getPassword());
        //验证验证码token(如果开启)
        try {

            Webconf webconf = webconfTransDao.queryWebconfByKey(WebConfDataConfig.IS_LOGIN_NEED_CAPTCHA);
            if (webconf != null && webconf.getBooleanValue()) {
                String accessToken = (String) param.get(Constants.CAPTCHA_TOKEN);
                String tokenStore = cacheStore.get(Constants.LOGIN_CAPTCHA_AUTH).orElse(null);
                boolean isCaptcha = null != accessToken && accessToken.equals(tokenStore);
                if (!isCaptcha) {
                    throw new JwBlogException(ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID,
                            ExceptionConstants.LOGIN_CAPTCHA_AUTH_INVALID_DESC);
                }
            }

        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
        // 验证密码
        if (!isValid) {
            throw new BadCredentialsException(Constants.WRONG_PASSWORD);
        }

        //更新用户登录信息，生成ACCESS TOKEN
        try {
            AuthToken authToken = adminBizService.authLogin(userName,
                    (String) param.get(Constants.LOGIN_IP));
            jwAuthenticationToken.getParam().put(Constants.AUTH_TOKEN, authToken);
            user.setAuthToken(authToken);
        } catch (JwBlogException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

        return new JwAuthenticationToken(user.getAuthorities(), user);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence formPwd) {
                return DigestUtils.md5DigestAsHex(formPwd.toString().getBytes());
            }

            @Override
            public boolean matches(CharSequence formPwd, String password) {
                return password.equals(encode(formPwd));
            }
        };
    }
}
