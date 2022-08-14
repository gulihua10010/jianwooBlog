package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.PageId;
import cn.jianwoo.blog.annotation.SubToken;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.exception.FormDuplicateSubmitException;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.ProcessTokenUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author gulihua
 * @Description AOP切面 禁止重复提交表单
 * @date 2021-01-07 10:44 下午
 */
@Aspect
@Component
@Slf4j
public class AvoidDuplicateSubmissionAspect {
    public static final String REQUEST_ID = "requestId";
    public static final String PAGE_ID = "PAGE_ID";
    public static final String OPEN = "_OPEN";

    @Autowired
    private ProcessTokenUtil processTokenUtil;
    @Autowired
    private CacheStore<String, String> cacheStore;

    @Pointcut("@annotation(subToken)")
    public void doToken(SubToken subToken) {

    }


    @Around(value = "doToken(subToken) && @annotation(pageId)")
    public Object validateToken(ProceedingJoinPoint joinPoint, SubToken subToken, PageId pageId) throws Throwable {
        log.info("===>> AvoidDuplicateSubmissionAspect validateToken method: {}", joinPoint.getSignature().getName());

        String clientToken = getClientToken(joinPoint);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String page = PAGE_ID;
        if (pageId != null) {
            page = pageId.value().getValue();
        }
        String key = processTokenUtil.getSubTokenKey(request, page);
        key = key.concat(Constants.SEPARATE_HYPHEN).concat(clientToken);

        if (null != subToken) {
            if (subToken.validateToken()) {
                boolean isDuplicate = isRepeatSubmit(key);
                if (isDuplicate) {
                    return processException(FormDuplicateSubmitException.FORM_DUPLICATE_SUBMIT_EXCEPTION);
                }
            }
        }
        String responseDto = (String) joinPoint.proceed();
        if (StringUtils.isNotBlank(responseDto)) {
            try {
                BaseResponseDto result = JSONObject.parseObject(responseDto, BaseResponseDto.class);
                if (!result.isSuccess()) {
                    if (cacheStore.hasKey(key)) {
                        cacheStore.delete(key);
                    }
                }
            } catch (Exception e) {
                log.error("Parameter conversion failed, JSON string exception: e" + e.getMessage(), e);
            }
        }

        return responseDto;
    }


    private String processException(JwBlogException e) {
        return JSON.toJSONString(new BaseResponseDto(e.getCode(), e.getMsg()));
    }


    private boolean isRepeatSubmit(String key) {

        if (cacheStore.hasKey(key)) {
            return true;
        }
        cacheStore.put(key, Constants.TRUE, 2, TimeUnit.HOURS);
        return false;
    }

    private String getClientToken(JoinPoint joinPoint) {
        String clinetToken = null;
        if (joinPoint.getArgs().length > 0) {
            for (Object o : joinPoint.getArgs()) {
                if (o instanceof String) {
                    String paramStr = (String) o;
                    if (!paramStr.contains(REQUEST_ID)) {
                        continue;
                    }
                    JSONObject parameter = JSON.parseObject(paramStr);
                    if (null != parameter) {
                        clinetToken = (String) parameter.get(REQUEST_ID);
                        break;
                    }
                }

            }
        }
        return clinetToken;

    }


}
