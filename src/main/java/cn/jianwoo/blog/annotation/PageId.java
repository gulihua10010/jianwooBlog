package cn.jianwoo.blog.annotation;

import cn.jianwoo.blog.enums.PageIdEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface PageId {
    PageIdEnum value() default PageIdEnum.ADMIN_INDEX;
}
