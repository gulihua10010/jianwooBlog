package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.exception.FormDuplicateSubmitException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.TokenProcessorUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gulihua
 * @Description
 * @date 2021-01-07 10:44 下午
 */
@Aspect
@Component
public class AvoidDuplicateSubmissionAspect {
    private static final Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionAspect.class);
    public static final String TOKEN_KEY = "SUB_TOEKN_KEY";

    @Pointcut("@annotation(subToken)")
    public void subToken(SubToken subToken) {

    }

    private String getSubTokenKey(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key = new StringBuilder(TOKEN_KEY);
        key.append(Constants.SEPARATE_HYPHEN).append(methodName);
        return key.toString();
    }

    @Around(value = "subToken(subToken)")
    public void doBefore(ProceedingJoinPoint joinPoint, SubToken subToken) throws JwBlogException {
        logger.info("===>> Before method: {}", joinPoint.getSignature().getName());
        if (null != subToken) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            if (subToken.saveToken()) {
                String key = getSubTokenKey(joinPoint);
                Object o = request.getSession().getAttribute(key);
                if (null == o) {
                    request.getSession().setAttribute(key, TokenProcessorUtil.generateToken(request));

                } else {
                    throw FormDuplicateSubmitException.FORM_DUPLICATE_SUBMIT_EXCEPTION;

                }

            }

        }

    }

    @AfterReturning(value = "subToken(subToken)")
    public void doAfter(JoinPoint joinPoint, SubToken subToken) {
        logger.info("===>> After method: {}", joinPoint.getSignature().getName());

        if (null != subToken) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            if (subToken.saveToken()) {
                String key = getSubTokenKey(joinPoint);
                Object o = request.getSession().getAttribute(key);
                if (null != o) {
                    request.getSession().removeAttribute(key);
                }
            }

        }
    }
}
