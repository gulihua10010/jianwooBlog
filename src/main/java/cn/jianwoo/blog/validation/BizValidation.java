package cn.jianwoo.blog.validation;

import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.exception.ValidationException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author GuLihua
 * @Description
 * @date 2020-08-04 16:03
 */
public class BizValidation {
    private static final Logger logger = LoggerFactory.getLogger(BizValidation.class);

    public static void paramValidate(String paramValue, String paramName) throws ValidationException {
        if (StringUtils.isBlank(paramValue)) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .formatMsg("Parameter verified failed, the value is empty in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(List paramValue, String paramName) throws ValidationException {
        if (CollectionUtils.isEmpty(paramValue)) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .formatMsg("Parameter verified failed, the list is empty in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(Object[] paramValue, String paramName) throws ValidationException {
        if (null == paramValue || paramName.length() == 0) {
            throw ValidationException.VALIDATOR_ARRAY_PARAM_IS_EMPTY
                    .formatMsg(ValidationException.DEFAULT_VALIDATION_MSG + " in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(Object paramValue, String paramName) throws ValidationException {
        if (null == paramValue) {
            throw ValidationException.VALIDATOR_PARAM_IS_NULL
                    .getNewInstance(ValidationException.DEFAULT_VALIDATION_MSG + " in the parameter: %s", paramName)
                    .print();
        }

    }


    public static void paramValidate(String paramValue, String paramName, String msg) throws ValidationException {
        if (StringUtils.isBlank(paramValue)) {
            logger.error("Parameter verified failed, the value is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, msg, paramName);

        }

    }


    public static void paramValidate(List paramValue, String paramName, String msg) throws ValidationException {
        if (CollectionUtils.isEmpty(paramValue)) {
            logger.error("Page parameter verified failed, the list is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_LIST_EMPTY, msg, paramName);
        }

    }


    public static void paramValidate(Object[] paramValue, String paramName, String msg) throws ValidationException {
        if (null == paramValue || paramValue.length == 0) {
            logger.error("Parameter verified failed, the array is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_ARRAY_EMPTY, msg, paramName);

        }

    }

    public static void paramLengthValidate(String paramValue, Integer length, String paramName, String msg) throws ValidationException {
        if (null != paramValue && paramValue.length() > length) {
            logger.error("Parameter verified failed, the length({}) of parameter '{}' is greater than {}. ", paramValue.length(), paramName, length);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_STRING_LENGTH, msg, paramName);

        }

    }
    public static void paramMinLengthValidate(String paramValue, Integer length, String paramName, String msg) throws ValidationException {
        if (null != paramValue && paramValue.length() < length) {
            logger.error("Parameter verified failed, the length({}) of parameter '{}' is letter than {}. ", paramValue.length(), paramName, length);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_STRING_LENGTH, msg, paramName);

        }

    }

    public static void paramNumberMinValidate(String paramValue, String min, String paramName, String msg) throws ValidationException {
        BigDecimal v;
        try {
            v = new BigDecimal(paramValue);
        } catch (Exception e) {
            String errMsg = String.format("Parameter verified failed, the value[%s] of parameter '%s' is not a number. ", paramValue, paramName);
            logger.error(errMsg);
            throw new ValidationException(ExceptionConstants.VALIDATOR_NUMBER, String.format(ExceptionConstants.VALIDATOR_NUMBER_DESC, paramName), paramName);
        }
        BigDecimal minV;
        try {
            minV = new BigDecimal(min);
        } catch (Exception e) {
            String errMsg = String.format("The value of min '%s' is not a number. ", min);
            logger.error(errMsg);
            minV = new BigDecimal("0");
        }
        if (v.compareTo(minV) < 0) {
            logger.error("Parameter verified failed, the value[{}] of parameter '{}' is letter than {}. ", paramValue, paramName, min);
            throw new ValidationException(ExceptionConstants.VALIDATOR_NUMBER_MIN, msg, paramName);

        }

    }

    public static void paramNumberMaxValidate(String paramValue, String max, String paramName, String msg) throws ValidationException {
        BigDecimal v;
        try {
            v = new BigDecimal(paramValue);
        } catch (Exception e) {
            String errMsg = String.format("Parameter verified failed, the value[%s] of parameter '%s' is not a number. ", paramValue, paramName);
            logger.error(errMsg);
            throw new ValidationException(ExceptionConstants.VALIDATOR_NUMBER, String.format(ExceptionConstants.VALIDATOR_NUMBER_DESC, paramName), paramName);
        }
        BigDecimal maxV;
        try {
            maxV = new BigDecimal(max);
        } catch (Exception e) {
            String errMsg = String.format("The value of max '%s' is not a number. ", max);
            logger.error(errMsg);
            maxV = new BigDecimal("0");
        }
        if (v.compareTo(maxV) > 0) {
            logger.error("Parameter verified failed, the value[{}] of parameter '{}' is greater than {}. ", paramValue, paramName, max);
            throw new ValidationException(ExceptionConstants.VALIDATOR_NUMBER_MAX, msg, paramName);

        }

    }

    public static void paramRegexValidate(String paramValue, String regex, String paramName, String msg) throws ValidationException {
        if (StringUtils.isNotBlank(paramValue) && !Pattern.matches(regex, paramValue)) {
            logger.error("Parameter verified failed, the regular expression is {}, but field[{}] value is {}. ", regex, paramName, paramValue);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_STRING_REGEX, msg, paramName);

        }

    }

    public static void paramNumberValidate(String paramValue, String paramName, String msg) throws ValidationException {
        if (StringUtils.isNotBlank(paramValue)) {
            try {
                new BigDecimal(paramValue);
            } catch (Exception e) {
                logger.error("Parameter verified failed, the value[{}] of parameter '{}' is not a number", paramValue, paramName);
                throw new ValidationException(ExceptionConstants.VALIDATOR_NUMBER, msg, paramName);
            }
        }

    }


    public static void paramValidate(Object paramValue, String paramName, String msg) throws ValidationException {
        if (null == paramValue) {
            logger.error("Parameter verified failed, the value is empty in the parameter: {}", paramName);
            throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NULL, msg, paramName);

        }

    }


    public static void paramRangeValidate(Object paramValue, String paramName, String msg, Object... values)
            throws ValidationException {
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
                throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_NOT_IN_RANGE, msg, paramName);

            }
        }

    }


    public static void paramValidateListContent(List<String> paramValue, String paramName, String msg) throws ValidationException {
        if (!CollectionUtils.isEmpty(paramValue)) {
            for (String s : paramValue) {
                if (StringUtils.isBlank(s)) {
                    logger.error("Page parameter verified failed, some list content is empty in the parameter: {}", paramName);
                    throw new ValidationException(ExceptionConstants.VALIDATION_FAILED_LIST_CONTENT_EMPTY, msg, paramName);
                }
            }

        }

    }

    public static void paramFileSizeValidate(MultipartFile fileObj, Long maxSize, String msg) throws ValidationException {
        if (null != fileObj) {
            if (fileObj.getSize() > maxSize) {
                logger.error("Parameter verified failed,the file size exceeds the maximum limit: {}, current size: {}", maxSize, fileObj.getSize());
                throw ValidationException.VALIDATOR_FILE_SIZE_MAX
                        .formatMsg(msg, maxSize)
                        .print();
            }
        }

    }


}
