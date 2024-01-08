package com.project.template.common.cache.enums;

import lombok.Getter;

@Getter
public enum CacheFailEnums {

    FAIL(300, "缓存出现异常"),
    VALUE_EMPTY(300, "值不存在"),
    PASSWORD_EXPIRATION(201, "账号密码已过期，请修改密码"),
    LOGIN_EXPIRATION(400, "登录已过期，请重新登录"),
    CODE_ERROR(210, "验证码错误"),
    FETCH_USERINFO_ERROR(219, "获取用户信息失败"),
    LOGIN_AUTH(201, "用户未登录"),
    ;

    private final Integer code;
    private final String message;

    CacheFailEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
