package cn.jianwoo.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GuLihua
 * @Description Bean 拷贝扩展
 * @date 2021-06-01 15:35
 */
public class CopyBeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(CopyBeanUtil.class);

    /**
     * copyProperties扩展;<br/>
     * 支持自定义类型的属性 (eg:从BO复制到VO,或者VO复制到BO);<br/>
     * 支持list复制(必须指定泛型类型);<br/>
     * 属性必须相同，get和set的方法名必须相同
     *
     * @param source source Bean
     * @param target target Bean
     * @return
     * @author gulihua
     */
    public static void copyProperties(Object source, Object target) throws BeansException {
        try {
            Assert.notNull(source, "Source must not be null");
            Assert.notNull(target, "Target must not be null");
            BeanUtils.copyProperties(source, target);
            Method[] sourceMethods = source.getClass().getDeclaredMethods();
            for (Method sourceMethod : sourceMethods) {
                if (!(sourceMethod.getName().startsWith("get"))) {
                    continue;
                }
                Class clazz = sourceMethod.getReturnType();
                // 查询自定义类型的属性方法
                if (clazz.getName().startsWith("cn.jianwoo.blog")) {
                    try {
                        Object sourceObj = sourceMethod.invoke(source);
                        if (sourceObj == null) continue;
                        Method targetMethod = target.getClass().getDeclaredMethod(sourceMethod.getName());
                        Object newObj = targetMethod.getReturnType().newInstance();
                        copyProperties(sourceObj, newObj);
                        Method setTargetMethod = target.getClass().getDeclaredMethod(
                                sourceMethod.getName().replace("get", "set"), targetMethod.getReturnType());
                        setTargetMethod.invoke(target, newObj);
                    } catch (Exception e) {
                        logger.error("====>>>CopyBeanUtil.copyProperties copy prop [{}] failed, e:\r\n{}", sourceMethod.getName(), e);
                        continue;
                    }

                }
                // 查询list类型的属性
                else if ("java.util.List".equals(clazz.getName())) {
                    try {
                        List sourceObj = (List) sourceMethod.invoke(source);
                        if (CollectionUtils.isEmpty(sourceObj)) continue;
                        List newList = new ArrayList();
                        Method targetMethod = target.getClass().getDeclaredMethod(sourceMethod.getName());
                        Type type = targetMethod.getGenericReturnType();

                        String typename = null;
                        for (Object o : sourceObj) {
                            Object newObj = new Object();
                            // list中含泛型类型
                            if (type instanceof ParameterizedType) {
                                ParameterizedType parameterizedType = (ParameterizedType) type;
                                typename = parameterizedType.getActualTypeArguments()[0].getTypeName();
                                newObj = Class.forName(typename).newInstance();
                            }
                            copyProperties(o, newObj);
                            newList.add(newObj);
                        }
                        if (null != typename && !typename.startsWith("cn.jianwoo.blog")) continue;

                        Method setTargetMethod = target.getClass().getDeclaredMethod(
                                sourceMethod.getName().replace("get", "set"), targetMethod.getReturnType());
                        setTargetMethod.invoke(target, newList);
                    } catch (Exception e) {
                        logger.error("====>>>CopyBeanUtil.copyProperties copy prop [{}] failed, e:\r\n{}", sourceMethod.getName(), e);
                        continue;
                    }

                }

            }
        } catch (Exception e) {
            logger.error("====>>>CopyBeanUtil.copyProperties exec failed, e:\r\n", e);
            throw new FatalBeanException("Could not copy property from source to target");
        }

    }
}
