package cn.jianwoo.blog.validation;

import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.exception.ValidationException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 16:03
 */
public class BizValidation {
    private static final Logger logger = LoggerFactory.getLogger(BizValidation.class);

    public static void paramValidate(String paramValue, String paramName) throws JwBlogException {
        if (StringUtils.isBlank(paramValue)) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .getNewInstance("Parameter verified failed, the value is empty in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(List paramValue, String paramName) throws JwBlogException {
        if (CollectionUtils.isEmpty(paramValue)) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .getNewInstance("Parameter verified failed, the list is empty in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(Object[] paramValue, String paramName) throws JwBlogException {
        if (null == paramValue || paramName.length() == 0) {
            throw ValidationException.VALIDATOR_ARRAY_PARAM_IS_EMPTY
                    .getNewInstance(ValidationException.DEFAULT_VALIDATION_MSG + " in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(Object paramValue, String paramName) throws JwBlogException {
        if (null == paramValue) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .getNewInstance(ValidationException.DEFAULT_VALIDATION_MSG + " in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(String paramValue, String paramName, String msg) throws JwBlogException {
        if (StringUtils.isBlank(paramValue)) {
            logger.error("Parameter verified failed, the value is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, msg);

        }

    }


    public static void paramValidate(List paramValue, String paramName, String msg) throws JwBlogException {
        if (CollectionUtils.isEmpty(paramValue)) {
            logger.error("Page parameter verified failed, the list is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_LIST_EMPTY, msg);
        }

    }


    public static void paramValidate(Object[] paramValue, String paramName, String msg) throws JwBlogException {
        if (null == paramValue || paramName.length() == 0) {
            logger.error("Parameter verified failed, the array is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_ARRAY_EMPTY, msg);

        }

    }


    public static void paramValidate(Object paramValue, String paramName, String msg) throws JwBlogException {
        if (null == paramValue) {
            logger.error("Parameter verified failed, the value is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, msg);

        }

    }


    public static void paramRangeValidate(Object paramValue, String paramName, String msg, Object... values)
            throws JwBlogException {
        boolean valid = false;
        if (null != paramValue) {
            for (Object o : values) {
                if (paramValue.equals(o)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) {
                logger.error("Parameter {} verified failed, the value {} is not in range: {}", paramName, paramValue,
                        JSON.toJSONString(values));
                throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NOT_IN_RANGE, msg);

            }
        }

    }

}