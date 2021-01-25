package cn.jianwoo.blog.base;

import cn.jianwoo.blog.constants.ExceptionConstants;
import cn.jianwoo.blog.exception.JwBlogException;
import cn.jianwoo.blog.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = 6875048159436708207L;
    private static final String INTEGER = "java.lang.Integer";
    private static final String DATE = "java.util.Date";
    private static final String LONG = "java.lang.Long";
    private static final String STRING = "java.lang.String";
    private static final String BIG_DECIMAL = "java.math.BigDecimal";
    private static final String LIST = "java.util.List";

    public void trimAllString() throws Exception {
        Method[] methods = this.getClass().getMethods();
        if (methods != null && methods.length > 0) {
            for (int i = 0; i < methods.length; i++) {
                Method method = methods[i];

                if (method.getName().equals("getCustomIdentification") || method.getName().equals("getLogicalKey")
                        || !(method.getName().startsWith("get"))) {
                    continue;
                } //

                Object resultObj = method.invoke(this);

                if (!(resultObj instanceof String)) {
                    continue;
                }

                String resultStr = (String) resultObj;
                String setMethodName = "set" + method.getName().substring(3);

                try {
                    Method setMethod = this.getClass().getMethod(setMethodName, String.class);
                    setMethod.invoke(this, resultStr.trim());
                } catch (NoSuchMethodException e) {
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage());
                }
            }
        }
    }


    public void initTestData() throws Exception {
        Method[] methods = this.getClass().getMethods();
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
                Method setMethod = this.getClass().getMethod(setMethodName, String.class);
                setMethod.invoke(this, resultStr);
            } else if (INTEGER.equals(resultType)) {
                Integer resultStr = TestUtil.getInstance().getRandomInt(0, 1000);
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Integer.class);
                setMethod.invoke(this, resultStr);
            } else if (DATE.equals(resultType)) {
                Date resultStr = TestUtil.getInstance().getRandomDate();
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Date.class);
                setMethod.invoke(this, resultStr);
            } else if (LONG.equals(resultType)) {
                Long resultStr = TestUtil.getInstance().getRandomLong();
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Long.class);
                setMethod.invoke(this, resultStr);
            } else if (BIG_DECIMAL.equals(resultType)) {
                BigDecimal n = new BigDecimal(TestUtil.getInstance().getRandomInt(0, 1000));
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, BigDecimal.class);
                setMethod.invoke(this, n);
            }

        }

    }


    public void clearProperties() throws Exception {
        Method[] methods = this.getClass().getMethods();
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
                Method setMethod = this.getClass().getMethod(setMethodName, String.class);
                setMethod.invoke(this, new Object[]{null});
            } else if (INTEGER.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Integer.class);
                setMethod.invoke(this, new Object[]{null});
            } else if (DATE.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Date.class);
                setMethod.invoke(this, new Object[]{null});
            } else if (LONG.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, Long.class);
                setMethod.invoke(this, new Object[]{null});
            } else if (BIG_DECIMAL.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, BigDecimal.class);
                setMethod.invoke(this, new Object[]{null});
            } else if (LIST.equals(resultType)) {
                String setMethodName = "set" + method.getName().substring(3);
                Method setMethod = this.getClass().getMethod(setMethodName, List.class);
                setMethod.invoke(this, new Object[]{null});
            }

        }

    }


    public void setAllEmptyStringToNull() throws Exception {
        Method[] methods = this.getClass().getMethods();

        if (methods == null || methods.length == 0) {
            return;
        }

        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];

            if (method.getName().equals("getCustomIdentification") || method.getName().equals("getLogicalKey")
                    || !(method.getName().startsWith("get"))) {
                continue;
            }

            Object resultObj = method.invoke(this);

            if (!(resultObj instanceof String)) {
                continue;
            }

            String result = (String) resultObj;
            if ("".equals(result.trim())) {
                String setMethodName = "set" + method.getName().substring(3);
                try {
                    Method setMethod = this.getClass().getMethod(setMethodName, String.class);
                    setMethod.invoke(this, new Object[]{null});
                } catch (NoSuchMethodException e) {
                    throw new JwBlogException(ExceptionConstants.SYSTEM_EXCEPTION, e.getMessage());

                }
            }
        }
    }


    public Map<String, Object> toMapValues() {
        Map<String, Object> rlt = new HashMap<String, Object>();

        Method[] methods = this.getClass().getDeclaredMethods();
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
                resultObj = method.invoke(this);
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


    @Override
    public String toString() {
        try {
            return BeanUtils.describe(this).toString();
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

}
