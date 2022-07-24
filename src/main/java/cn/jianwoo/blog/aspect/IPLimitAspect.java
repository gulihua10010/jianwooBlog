package cn.jianwoo.blog.aspect;

import cn.jianwoo.blog.annotation.IpLimit;
import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.cache.CacheStore;
import cn.jianwoo.blog.constants.CacheKeyConstants;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.service.base.IpControlBaseService;
import cn.jianwoo.blog.service.base.LoadingCacheIpService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.text.MessageFormat;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-28 15:23
 */
@Component
@Scope
@Aspect
@Slf4j
public class IPLimitAspect {


    @Autowired
    private CacheStore<String, Object> cacheStore;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LoadingCacheIpService loadingCacheIpService;
    @Autowired
    private IpControlBaseService ipControlBaseService;
    @Value("${black.ip.warn.count}")
    private Integer ipWarnCount;

    @Pointcut("@annotation(cn.jianwoo.blog.annotation.IpLimit)")
    public void ipLimit() {

    }

    @Around("ipLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿limit的注解
        IpLimit limit = method.getAnnotation(IpLimit.class);
        if (limit != null) {
            Object obj = null;
            String ipAddr = request.getRemoteAddr();
            String key = ipAddr.concat(Constants.IP_SPLIT).concat(limit.key());
            if (limit.limit() != -1) {
                String cacheKey = MessageFormat.format(CacheKeyConstants.IIP_ACCESS_TRAFFIC_CTRL_KEY, limit.key());
                cacheStore.put(cacheKey, limit.limit());
            }

            RateLimiter limiter = loadingCacheIpService.getIpLimiter(key);
            if (limiter.tryAcquire(limit.timeout(), limit.timeunit())) {
                // 获得令牌（不限制访问）
                obj = joinPoint.proceed();
            } else {
                // 未获得令牌（限制访问）
                String cacheKey = MessageFormat.format(CacheKeyConstants.IP_BLACK_WARN_KEY, ipAddr);

                Integer warnCnt = 0;
                if (cacheStore.hasKey(cacheKey)) {
                    warnCnt = (Integer) cacheStore.get(cacheKey).orElse(0);
                }
                if (warnCnt > ipWarnCount  && !ipControlBaseService.isIpInBlackList(ipAddr)) {
                    ipControlBaseService.doCreateBlackRecord(ipAddr);
                }

                cacheStore.put(cacheKey, warnCnt + 1);

                responseFail();
            }
            return obj;
        }
        return joinPoint.proceed();

    }

    /**
     * 直接向前端抛出异常
     */
    private void responseFail() throws Exception {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(Constants.CONTENT_TYPE_JSON);
        response.getWriter().write(JSONObject.toJSONString(
                new BaseResponseDto(ExceptionConstants.BIZ_ACCESS_FREQUENTLY, ExceptionConstants.ACCESS_FREQUENTLY_DESC)));

    }

}
