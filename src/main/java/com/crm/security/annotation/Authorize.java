package com.crm.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author walker tu
 * @date 2017/9/29
 * @description：
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorize {

    // 定义权限
    String value()

            default "";

    // 是否忽略 ignore=true
    // 如果是忽略 则value 将失效
    boolean ignore()

            default false;
}
