package com.project.template.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AfterAspect {
    // 配置切点类
    // 配置切点类 拥有这个注解的都会被AOP增强
    @Pointcut("@annotation(com.project.template.common.annotation.BeforeAnnotation)")
    public void point(){

    }

    // 后置通知
    @AfterReturning("point()")
    public void after(JoinPoint joinPoint){
        // 前置通知开始
        System.out.println("=============后置通知=============");
    }
}
