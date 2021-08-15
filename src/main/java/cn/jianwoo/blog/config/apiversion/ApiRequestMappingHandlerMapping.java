package cn.jianwoo.blog.config.apiversion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author GuLihua
 * @Description
 * @date 2021-08-08 16:17
 */

public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {

        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }
}
