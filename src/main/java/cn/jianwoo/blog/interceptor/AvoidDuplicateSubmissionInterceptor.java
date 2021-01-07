package cn.jianwoo.blog.interceptor;

import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.util.TokenProcessorUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-07 16:09
 */
@Component
public class AvoidDuplicateSubmissionInterceptor extends
        HandlerInterceptorAdapter {
    public static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            logger.info("===>>AvoidDuplicateSubmissionInterceptor.preHandle: method Name: {}", method.getName());
            SubToken annotation = method.getAnnotation(SubToken.class);
            if (null != annotation) {
                boolean needSaveToken = annotation.saveToken();
                if (needSaveToken) {
                    request.getSession().setAttribute("subToken", TokenProcessorUtil.generateToken(request));
                }
                boolean needRemoveSession = annotation.removeToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        return false;
                    }
                    request.getSession().removeAttribute("subToken");

                }

            }

        }
        return true;
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession().getAttribute(
                "subToken");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = null;
        JSONObject parameter = JSON.parseObject(getPostData(request));
        if (null != parameter) {
            clinetToken = (String) parameter.get("subToken");
        }
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

    private static String getPostData(HttpServletRequest request) {
        StringBuffer data = new StringBuffer();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
        } catch (IOException e) {
        } finally {
        }
        return data.toString();
    }
}
