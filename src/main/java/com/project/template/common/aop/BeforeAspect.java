package com.project.template.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAspect {
    // 配置切点类
    // 配置切点类 拥有这个注解的都会被AOP增强
    @Pointcut("@annotation(com.project.template.common.annotation.BeforeAnnotation)")
    public void point(){
        
    }
    
    // 前置通知
    @Before("point()")
    public void begin(JoinPoint joinPoint){
        // 前置通知开始
        System.out.println("=============前置通知=============");
    }
}
