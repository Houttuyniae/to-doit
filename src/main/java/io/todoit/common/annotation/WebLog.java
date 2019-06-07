package io.todoit.common.annotation;

import java.lang.annotation.*;

/**
 * @className:WebLog
 * @author:zhangd
 * @date:2019/2/27
 * @description:TODO
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebLog {
    String value() default "";
}
