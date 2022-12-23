package com.project.template.common.annotation;


import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AfterAnnotation {
    String module() default "";         // 作用范围

    String operation() default "";      // 业务
}
