package com.crm.security.annotation;

import com.crm.commons.ResourceConstantEnum;

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
    ResourceConstantEnum value();

    // 是否忽略 ignore=true
    // 如果是忽略 则value 将失效
    boolean ignore()

            default false;
}
