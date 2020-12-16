package cn.jianwoo.blog.util;

import cn.jianwoo.blog.constants.Constants;
import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author GuLihua
 * @Description
 * @date 2020-09-29 10:42
 */
public class DomainUtil {
    protected static final Logger log = LoggerFactory.getLogger(DomainUtil.class);

    private static final long serialVersionUID = 6875048159436708207L;
    private static final String INTEGER = "java.lang.Integer";
    private static final String DATE = "java.util.Date";
    private static final String LONG = "java.lang.Long";
    private static final String STRING = "java.lang.String";
    private static final String BIG_DECIMAL = "java.math.BigDecimal";
    private static final String LIST = "java.util.List";

    public DomainUtil() {
    }


    public static void trimAllString(Object domainObj) throws Exception {
        Method[] methods = domainObj.getClass().getMethods();
        if (methods != null && methods.length > 0) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];

                if (!(method.getName().startsWith("get"))) {
                    continue;
                }

                Object resultObj = method.invoke(domainObj);

                if (!(resultObj instanceof String)) {
                    continue;
                }

                String resultStr = (String) resultObj;
                String setMethodName = "set" + method.getName().substring(3);

                try {
                    Method setMethod = domainObj.getClass().getMethod(setMethodName, String.class);
                    setMethod.invoke(domainObj, resultStr.trim());
                } catch (NoSuchMethodException e) {
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage());
                }
            }
        }
    }


    public static void initTestData(Object domainObj) throws Exception {
        Method[] methods = domainObj.getClass().getMethods();
        if (methods == null || methods.length == 0) {
            return;
        }

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if ("getOid".equals(method.getName()) || !(method.getName().startsWith("get"))) {
                continue;
            }
            Class<?> resultObj = method.getReturnType();
            String resultType = resultObj.getName();
            // System.out.println(resultObj.getName());
            if (STRING.equals(resultType) && !"getBooleanValue".equals(method.getName())) {
                String resultStr = TestUtil.getInstance().getRandomStr(10);
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, String.class);
                setMethod.invoke(domainObj, resultStr);
            } else if (INTEGER.equals(resultType)) {
                Integer resultStr = TestUtil.getInstance().getRandomInt(0, 1000);
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Integer.class);
                setMethod.invoke(domainObj, resultStr);
            } else if (DATE.equals(resultType)) {
                Date resultStr = TestUtil.getInstance().getRandomDate();
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Date.class);
                setMethod.invoke(domainObj, resultStr);
            } else if (LONG.equals(resultType)) {
                Long resultStr = TestUtil.getInstance().getRandomLong();
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Long.class);
                setMethod.invoke(domainObj, resultStr);
            } else if (BIG_DECIMAL.equals(resultType)) {
                BigDecimal n = new BigDecimal(TestUtil.getInstance().getRandomInt(0, 1000));
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, BigDecimal.class);
                setMethod.invoke(domainObj, n);
            }

        }

    }


    public static void clearProperties(Object domainObj) throws Exception {
        Method[] methods = domainObj.getClass().getMethods();
        if (methods == null || methods.length == 0) {
            return;
        }

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if (!(method.getName().startsWith("get"))) {
                continue;
            }
            Class<?> resultObj = method.getReturnType();
            String resultType = resultObj.getName();
            if (STRING.equals(resultType) && !"getBooleanValue".equals(method.getName())) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, String.class);
                setMethod.invoke(domainObj, new Object[]{null});
            } else if (INTEGER.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Integer.class);
                setMethod.invoke(domainObj, new Object[]{null});
            } else if (DATE.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Date.class);
                setMethod.invoke(domainObj, new Object[]{null});
            } else if (LONG.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, Long.class);
                setMethod.invoke(domainObj, new Object[]{null});
            } else if (BIG_DECIMAL.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, BigDecimal.class);
                setMethod.invoke(domainObj, new Object[]{null});
            } else if (LIST.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = domainObj.getClass().getMethod(setMethodName, List.class);
                setMethod.invoke(domainObj, new Object[]{null});
            }

        }

    }


    public static void setAllEmptyStringToNull(Object domainObj) throws Exception {
        Method[] methods = domainObj.getClass().getMethods();

        if (methods == null || methods.length == 0) {
            return;
        }

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if (!(method.getName().startsWith("get"))) {
                continue;
            }

            Object resultObj = method.invoke(domainObj);

            if (!(resultObj instanceof String)) {
                continue;
            }

            String result = (String) resultObj;
            if ("".equals(result.trim())) {
                String setMethodName = "set" + method.getName().substring(3);
                try {
                    Method setMethod = domainObj.getClass().getMethod(setMethodName, String.class);
                    setMethod.invoke(domainObj, new Object[]{null});
                } catch (NoSuchMethodException e) {
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage());

                }
            }
        }
    }


    public static Map<String, Object> toMapValues(Object domainObj) {
        Map<String, Object> rlt = new HashMap<String, Object>();

        Method[] methods = domainObj.getClass().getDeclaredMethods();
        if (methods == null || methods.length == 0) {
            return rlt;
        }

        for (Method method : methods) {
            if (!Modifier.isPublic(method.getModifiers())) {
                continue;
            }

            if (method.getName().equals("getCustomIdentification") || method.getName().equals("getLogicalKey")
                    || !(method.getName().startsWith("get"))) {
                continue;
            }

            Object resultObj = null;

            try {
                resultObj = method.invoke(domainObj);
            } catch (Exception e) {
                new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage()).print();
                continue;
            }

            char[] array = method.getName().substring(3).toCharArray();
            StringBuffer sb = new StringBuffer();

            for (char c : array) {
                if (Character.isUpperCase(c)) {
                    sb.append('_');
                    sb.append(c);
                } else {
                    sb.append(Character.toUpperCase(c));
                }
            }

            String key = sb.toString();

            if (key.startsWith("_")) {
                key = key.substring(1);
            }

            if (resultObj instanceof Enum<?>) {
                resultObj = resultObj.toString();
            }

            rlt.put(key, resultObj);
        }

        return rlt;
    }


    public static String fetchStringValue(Object domainObj, String fieldName) throws Exception {
        Object rltValue = null;
        Method fieldGetMethod = domainObj.getClass().getMethod("get" + fieldName);
        if (null != fieldGetMethod) {
            rltValue = fieldGetMethod.invoke(domainObj);
        }

        return null == rltValue ? null : (String) rltValue;
    }


    public static Integer fetchIntegerValue(Object domainObj, String fieldName) throws Exception {
        Object rltValue = null;
        Method fieldGetMethod = domainObj.getClass().getMethod("get" + fieldName);
        if (null != fieldGetMethod) {
            rltValue = fieldGetMethod.invoke(domainObj);
        }

        return null == rltValue ? null : (Integer) rltValue;
    }


    public static Date fetchDateValue(Object domainObj, String fieldName) throws Exception {
        Object rltValue = null;
        Method fieldGetMethod = domainObj.getClass().getMethod("get" + fieldName);
        if (null != fieldGetMethod) {
            rltValue = fieldGetMethod.invoke(domainObj);
        }

        return null == rltValue ? null : (Date) rltValue;
    }


    public static void fillStringValue(Object domainObj, String fieldName, String value) throws Exception {
        fieldName = fieldName.substring(0, 1).toUpperCase(Locale.US).concat(fieldName.substring(1));
        try {
            Method fieldSetMethod = domainObj.getClass().getMethod("set" + fieldName, String.class);
            if (null != fieldSetMethod) {
                fieldSetMethod.invoke(domainObj, value);
            }

        } catch (NoSuchMethodException var4) {
        }
    }


    public static void fillIntegerValue(Object domainObj, String fieldName, int value) throws Exception {
        Method fieldSetMethod = null;
        fieldName = fieldName.substring(0, 1).toUpperCase(Locale.US).concat(fieldName.substring(1));
        try {
            fieldSetMethod = domainObj.getClass().getMethod("set" + fieldName, Integer.class);
        } catch (NoSuchMethodException var5) {
            return;
        }

        if (null != fieldSetMethod) {
            fieldSetMethod.invoke(domainObj, value);
        }

    }


    public static void fillDateValue(Object domainObj, String fieldName, Date value) throws Exception {
        fieldName = fieldName.substring(0, 1).toUpperCase(Locale.US).concat(fieldName.substring(1));
        try {
            Method fieldSetMethod = domainObj.getClass().getMethod("set" + fieldName, Date.class);
            if (null != fieldSetMethod) {
                fieldSetMethod.invoke(domainObj, value);
            }

        } catch (NoSuchMethodException var4) {
        }
    }


    public static String toString(Object domainObj) {
        try {
            return JSON.toJSONString(domainObj);
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }


    public static void printLog(Object domainObj) {
        try {
            String str = JSON.toJSONString(domainObj);
            log.info("Print Domain data: {}", str);
        } catch (Exception exception) {
            log.error(">> DomainUtil.printLog exec failed, exception: \n", exception);
        }
    }


    public static String format(String str) {
        return StringUtils.isBlank(str) ? Constants.BLANK : str;
    }


    public static String format(String str, String replace) {
        return StringUtils.isBlank(str) ? replace : str;
    }
}
