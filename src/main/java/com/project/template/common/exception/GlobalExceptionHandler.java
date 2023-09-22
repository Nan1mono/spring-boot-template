package com.project.template.common.exception;

import com.project.template.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice // 拦截所有Controller，并对其产生的异常进行统一处理
public class GlobalExceptionHandler {

    /**
     * 拦截所有java可产生异常并进行统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> error(Exception e){
        log.error(e.getLocalizedMessage());
        return Result.fail();
    }

    /**
     * 拦截所有自定义异常
     * @param myException 自定义异常
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result<Void> error(MyException myException){
        log.error(myException.getMessage());
        Integer code = myException.getCode();
        String message = myException.getMessage();
        return Result.fail(code, message);
    }
}
