package com.template.project.common.aop;

import com.alibaba.fastjson.JSON;
import com.template.project.common.annotation.LogAnnotation;
import com.template.project.common.util.HttpContextUtils;
import com.template.project.common.util.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

@Component
@Aspect
@Slf4j
public class LogAspect {

    // 配置切点类 拥有这个注解的都会被AOP增强
    @Pointcut("@annotation(com.template.project.common.annotation.LogAnnotation)")
    public void point(){

    }

    // 配置增强方法，这里使用环绕通知
    @Around("point()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        this.begin();
        //执行方法
        Object result = joinPoint.proceed();
        this.end();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordLog(joinPoint, time);
        return result;
    }
    
    private String begin(){
        // 
        return null;
    }
    
    private String end(){
        return null;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}",logAnnotation.module());
        log.info("operation:{}",logAnnotation.operation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}",className + "." + methodName + "()");

//        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}",params);

        //获取request 设置IP地址
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.info("ip:{}", IpUtils.getIpAddr(request));


        log.info("excute time : {} ms",time);
        log.info("=====================log end================================");
    }
}
