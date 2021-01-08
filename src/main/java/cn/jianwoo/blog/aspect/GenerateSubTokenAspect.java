package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-01-08 15:49
 */
@Aspect
@Component
public class GenerateSubTokenAspect {

    private static final Logger logger = LoggerFactory.getLogger(GenerateSubTokenAspect.class);
    public static final String SUB_TOKEN = "subToken";
    public static final String PAGE_ID = "PAGE_ID";

    @Pointcut("execution(public * cn.jianwoo.blog.controller.backend.page.AdminPageController.*(..))")
    public void generateToken() {
    }

    @Before(value = "generateToken() && @annotation(pageId)")
    public void genToken(JoinPoint joinPoint, PageId pageId) {
        logger.info("===>> GenerateSubTokenAspect Before method: {}", joinPoint.getSignature().getName());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String page = PAGE_ID;
        if (pageId != null) {
            page = pageId.value().getValue();
        }
        ProcessTokenUtil.generateToken(request, page);
    }
}
