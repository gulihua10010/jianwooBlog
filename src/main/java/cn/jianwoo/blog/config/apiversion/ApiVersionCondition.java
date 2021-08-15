package cn.jianwoo.blog.config.apiversion;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-08 16:25
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    public int getApiVersion() {
        return this.apiVersion;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition apiVersionCondition) {
        return new ApiVersionCondition(apiVersionCondition.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        String v = httpServletRequest.getParameter("v");
        if (StringUtils.isBlank(v)) {
            v = "1";
        }

        Integer version = Integer.valueOf(v);
        if (version == this.apiVersion) {
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition apiVersionCondition, HttpServletRequest httpServletRequest) {
        return apiVersionCondition.getApiVersion() - this.apiVersion;
    }
}
