package com.template.project.common.exception;

import com.template.project.common.result.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 拦截所有Controller，并对其产生的异常进行统一处理
public class GlobalExceptionHandler {

    /**
     * 拦截所有java可产生异常并进行统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 拦截所有自定义异常
     * @param myException 自定义异常
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result error(MyException myException){
        myException.printStackTrace();
        Integer code = myException.getCode();
        String message = myException.getMessage();
        return Result.fail(code, message);
    }
}
