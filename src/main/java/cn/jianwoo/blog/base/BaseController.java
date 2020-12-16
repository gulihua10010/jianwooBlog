package cn.jianwoo.blog.base;

import cn.jianwoo.blog.exception.ControllerBizException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.ValidationException;
import cn.jianwoo.blog.util.DomainUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class BaseController implements Serializable {
    private static final long serialVersionUID = -4016109185391802117L;

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    @Autowired
    protected ServletContext application;

    protected void printRequestParams(String param) {
        logger.info("receive the request with param :: {}", param);
    }


    protected <T> T convertParam(String param, Class<T> class1) throws ControllerBizException {
        T result;
        if (StringUtils.isBlank(param)) {
            throw ControllerBizException.JSON_IS_NULL.print();
        }
        try {
            result = JSONObject.parseObject(param, class1);
        } catch (Exception e) {
            throw ControllerBizException.JSON_CONVERT_ERROR.print();
        }
        try {
            DomainUtil.fillStringValue(result, "requestId", getRequestId());
            DomainUtil.fillStringValue(result, "actor", request.getRemoteUser());
            DomainUtil.fillStringValue(result, "clientIp", request.getRemoteAddr());
            DomainUtil.fillStringValue(result, "sessionId", request.getRequestedSessionId());
            DomainUtil.fillDateValue(result, "requestDate", new Date());
        } catch (Exception e) {
            throw ControllerBizException.JSON_CONVERT_ERROR.print();

        }
        return result;
    }


    private String getRequestId() {
        String ch = "1234567890";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            sb.append(ch.charAt(Math.abs(random.nextInt()) % (ch.length())));
        }
        return sb.toString();

    }


    protected String exceptionToString(Exception e) {
        logger.error("Occur system error, ex : " + e.getMessage(), e);

        if (e instanceof ControllerBizException) {
            return responseToJSONString(
                    new BaseResponseDto(((ControllerBizException) e).getCode(), ((ControllerBizException) e).getMsg()));
        } else if (e instanceof ValidationException) {
            return responseToJSONString(
                    new BaseResponseDto(((ValidationException) e).getCode(), ((ValidationException) e).getMsg()));
        } else if (e instanceof JwBlogException) {
            return responseToJSONString(
                    new BaseResponseDto(((JwBlogException) e).getCode(), ((JwBlogException) e).getMsg()));
        } else {
            return responseToJSONString(BaseResponseDto.SYSTEM_ERROR);
        }
    }


    protected BaseResponseDto exceptionToRespDto(Exception e) {
        if (e instanceof ControllerBizException) {
            return new BaseResponseDto(((ControllerBizException) e).getCode(), ((ControllerBizException) e).getMsg());
        } else if (e instanceof ValidationException) {
            return new BaseResponseDto(((ValidationException) e).getCode(), ((ValidationException) e).getMsg());
        } else if (e instanceof JwBlogException) {
            return new BaseResponseDto(((JwBlogException) e).getCode(), ((JwBlogException) e).getMsg());
        } else {
            logger.error("SYSTEM ERROR : " + e.getMessage(), e);
            return BaseResponseDto.SYSTEM_ERROR;
        }
    }


    protected String responseToJSONString(Object object) {
        String resp = "";
        if (null == object) {
            return resp;
        } else {
            resp = JSON.toJSONString(object);
        }
        logger.info("API response data: {}", resp);
        return resp;
    }


    public String getServerIPPort() {
        // + ":" + request.getServerPort()
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
