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

/**
 * @author GuLihua
 * @Description
 * @date 2021-05-06 15:08
 */
@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

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
        String accessToken = request.getParameter(Constants.ACCESS_TOKEN);


        Map<String, Object> map = new HashMap<>();
        map.put(Constants.LOGIN_IP, request.getRemoteAddr());
        map.put(Constants.ACCESS_TOKEN, accessToken);
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

        // 将用户id放入JWT token
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.USER_KEY, user.getAuthToken().getUid());
        map.put(Constants.ACCESS_TOKEN, user.getAuthToken().getAccessToken());
        map.put(Constants.REFRESH_TOKEN, user.getAuthToken().getRefreshToken());
        String token = JwtUtils.sign(map, user.getAuthToken().getExpiredIn() * 1000);
        // 将token放入响应头中
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.addHeader(Constants.ACCESS_TOKEN, token);
        response.getWriter().write(processSuccessMsg(Constants.SUCCESS_LOGIN));

        log.info("==>>LoginFilter.successfulAuthentication end...");

    }


    private String processException(Exception e) {
        return JSON.toJSONString(new BaseResponseDto(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage()));
    }

    private String processSuccessMsg(String msg) {
        return JSON.toJSONString(new BaseResponseDto(msg));
    }
}
