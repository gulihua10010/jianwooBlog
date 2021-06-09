package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.exception.FormDuplicateSubmitException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gulihua
 * @Description AOP切面 禁止重复提交表单
 * @date 2021-01-07 10:44 下午
 */
@Aspect
@Component
@Slf4j
public class AvoidDuplicateSubmissionAspect {
    public static final String SUB_TOKEN = "subToken";
    public static final String PAGE_ID = "PAGE_ID";
    public static final String OPEN = "_OPEN";


    @Pointcut("@annotation(subToken)")
    public void doToken(SubToken subToken) {

    }


    @Around(value = "doToken(subToken) && @annotation(pageId)")
    public Object validateToken(ProceedingJoinPoint joinPoint, SubToken subToken, PageId pageId) throws Throwable {
        log.info("===>> AvoidDuplicateSubmissionAspect validateToken method: {}", joinPoint.getSignature().getName());

        String clinetToken = getClientToken(joinPoint);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String page = PAGE_ID;
        if (pageId != null) {
            page = pageId.value().getValue();
        }
        String key = ProcessTokenUtil.getSubTokenKey(request, page);
        if (null != subToken) {
            if (subToken.validateToken()) {
                boolean isDuplicate = isRepeatSubmit(clinetToken, request, page);
                if (isDuplicate) {
                    request.getSession().removeAttribute(key);
                    return processException(FormDuplicateSubmitException.FORM_DUPLICATE_SUBMIT_EXCEPTION);
                }
                request.getSession().removeAttribute(key);
            }
        }
        request.getSession().removeAttribute(page);
        String responseDto = (String) joinPoint.proceed();
        BaseResponseDto dto = JSON.parseObject(responseDto, BaseResponseDto.class);
        if (!dto.isSuccess()) {
            request.getSession().setAttribute(key, clinetToken);
        }
        return responseDto;
    }


    private String processException(JwBlogException e) {
        return JSON.toJSONString(new BaseResponseDto(e.getCode(), e.getMsg()));
    }


    private boolean isRepeatSubmit(String clinetToken, HttpServletRequest request, String pageId) {
        String key = ProcessTokenUtil.getSubTokenKey(request, pageId);
        String serverToken = (String) request.getSession().getAttribute(
                key);
        if (serverToken == null) {
            return true;
        }
        log.info("serverToken: {}", serverToken);
        log.info("clinetToken: {}", clinetToken);

        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }

    private String getClientToken(JoinPoint joinPoint) {
        String clinetToken = null;
        if (joinPoint.getArgs().length > 0) {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof String) {
                    String paramStr = (String) o;
                    if (!paramStr.contains(SUB_TOKEN)) {
                        continue;
                    }
//                    System.out.println("paramStr");
//                    System.out.println(paramStr);
                    JSONObject parameter = JSON.parseObject(paramStr);
                    if (null != parameter) {
                        clinetToken = (String) parameter.get(SUB_TOKEN);
                        break;
                    }
                }

            }
        }
        return clinetToken;

    }


}
