package com.project.template.security.enums;

import lombok.Getter;

@Getter
public enum AuthFailEnum {

    PASSWORD_ERROR(201, "账号或密码错误"),
    PASSWORD_EXPIRATION(201, "账号密码已过期，请修改密码"),
    LOGIN_EXPIRATION(400, "登录已过期，请重新登录"),
    CODE_ERROR(210, "验证码错误"),
    FETCH_USERINFO_ERROR(219, "获取用户信息失败"),
    LOGIN_AUTH(201, "用户未登录"),
    ;

    private final Integer code;
    private final String message;

    AuthFailEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
