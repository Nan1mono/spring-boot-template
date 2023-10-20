package com.project.template.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    //    PARAM_ERROR( 202, "参数不正确"),
//    SERVICE_ERROR(203, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    //    PERMISSION(209, "没有权限"),


    ;

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
