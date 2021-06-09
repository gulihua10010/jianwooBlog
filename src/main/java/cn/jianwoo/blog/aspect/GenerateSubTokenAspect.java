package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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
@Slf4j
public class GenerateSubTokenAspect {

    public static final String SUB_TOKEN = "subToken";
    public static final String PAGE_ID = "PAGE_ID";
    public static final String OPEN = "_OPEN";

    @Pointcut("execution(public * cn.jianwoo.blog.controller.admin.page.AdminPageController.*(..))")
    public void generateToken() {
    }

    @Before(value = "generateToken() && @annotation(pageId)")
    public void genToken(JoinPoint joinPoint, PageId pageId) {
        log.info("===>> GenerateSubTokenAspect Before method: {}", joinPoint.getSignature().getName());

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String page = PAGE_ID;
        if (pageId != null) {
            page = pageId.value().getValue();

        }
        ProcessTokenUtil.generateToken(request, page);
    }


    @After(value = "generateToken() && @annotation(pageId)")
    public void doAfter(JoinPoint joinPoint, PageId pageId) {
        log.info("===>> GenerateSubTokenAspect After method: {}", joinPoint.getSignature().getName());
    }

}
