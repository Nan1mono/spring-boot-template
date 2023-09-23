package com.project.template.common.exception;

import com.project.template.common.result.ResultCodeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义全局异常类
 *
 * @author qy
 */
@Getter
@Slf4j
public class MyException extends RuntimeException {

    private final Integer code;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param message   exception message
     * @param code      exception code
     */
    public MyException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum exceptionCodeEnum
     */
    public MyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public MyException(Exception e) {
        super(e.getMessage());
        this.code = 201;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
