package com.project.template.common.annotation;

import java.lang.annotation.*;

/**
 * AOP切点注解类
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";         // 作用范围

    String operation() default "";      // 业务
}