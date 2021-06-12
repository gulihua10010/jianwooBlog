package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.security.token.AuthUserTokenBO;
import cn.jianwoo.blog.util.DateUtil;
import cn.jianwoo.blog.util.JwtUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 15:08
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    private final static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");
    public static String refreshTokenExpireDays = RESOURCE_BUNDLE.getString("refresh.token.expired.days");


    public LoginFilter() {
    }


    public LoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        log.info("==>>LoginFilter.attemptAuthentication...");
        String username = request.getParameter(Constants.USERNAME);
        String password = request.getParameter(Constants.PASSWORD);
        String accessToken = request.getParameter(Constants.CAPTCHA_TOKEN);


        Map<String, Object> map = new HashMap<>();
        map.put(Constants.LOGIN_IP, request.getRemoteAddr());
        map.put(Constants.CAPTCHA_TOKEN, accessToken);
        return authenticationManager.authenticate(new JwAuthenticationToken(
                null, username, password, map));


    }


    @Override
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String username = request.getParameter(Constants.USERNAME);
        String password = request.getParameter(Constants.PASSWORD);
        log.info("user {} login failed, password is {}, ip is {}, time is {}.", username, password,
                request.getRemoteAddr(), DateUtil.getNowStandardFormat());
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.getWriter().write(processException(exception));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("==>>LoginFilter.successfulAuthentication start...");

        AuthUserTokenBO user = (AuthUserTokenBO) authResult.getPrincipal();
        //将userId存入session
        request.getSession().setAttribute(Constants.CURRENT_USER, user.getAuthToken().getUid());
        String accessToken = request.getParameter(Constants.ACCESS_TOKEN);
        String refreshToken = request.getParameter(Constants.REFRESH_TOKEN);
        generateAccessToken(response,user);
        generateRefreshToken(response,user);

        response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGIN));

        log.info("==>>LoginFilter.successfulAuthentication end...");

    }

    private void generateAccessToken(HttpServletResponse response,AuthUserTokenBO user)
    {
        // 将用户id放入JWT token
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.USER_KEY, user.getAuthToken().getUid());
        map.put(Constants.ACCESS_TOKEN, user.getAuthToken().getAccessToken());
        String token = JwtUtils.sign(map, user.getAuthToken().getExpiredIn() * 1000);
        // 将token放入响应头中
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.addHeader(Constants.ACCESS_TOKEN, token);
    }

    private void generateRefreshToken(HttpServletResponse response,AuthUserTokenBO user)
    {
        // 将用户id放入JWT token
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.USER_KEY, user.getAuthToken().getUid());
        map.put(Constants.REFRESH_TOKEN, user.getAuthToken().getRefreshToken());
        String token = JwtUtils.sign(map, Long.parseLong(refreshTokenExpireDays) * 24 * 60 * 60 * 1000);
        // 将token放入响应头中
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.addHeader(Constants.REFRESH_TOKEN, token);
    }


    private String processException(Exception e) {
        return JSON.toJSONString(new BaseResponseDto(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage()));
    }

    private String processSuccessMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(msg));
    }
}
