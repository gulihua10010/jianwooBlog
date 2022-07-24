package cn.jianwoo.blog.filter;

import cn.jianwoo.blog.base.BaseResponseDto;
import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.service.base.IpControlBaseService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gulihua
 * @Description
 * @date 2022-04-15 15:11
 */

@Component

@Slf4j
public class IpControlFilter implements Filter {
    @Autowired
    private IpControlBaseService ipControlBaseService;



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String ip = request.getRemoteAddr();
        String url = httpServletRequest.getRequestURI();
        log.info("IpControlFilter::IP={}, URI={}", ip, url);

        if (ipControlBaseService.isIpInBlackList(ip)) {
            log.info("IpControlFilter::IP={} is in black list", ip);
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            httpServletResponse.setContentType(Constants.CONTENT_TYPE_JSON);
            httpServletResponse.getWriter().write(JSONObject.toJSONString(
                    new BaseResponseDto(ExceptionConstants.BIZ_ACCESS_REFUSED, ExceptionConstants.ACCESS_REFUSED_DESC)));
            return;
        }
        ipControlBaseService.doCreateRecord(ip, url);
        chain.doFilter(request, response);
    }
}
