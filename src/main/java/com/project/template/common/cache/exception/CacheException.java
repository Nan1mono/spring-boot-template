package com.project.template.common.cache.exception;

import com.project.template.security.enums.LoginFailEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CacheException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public CacheException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CacheException(LoginFailEnum loginFailEnum) {
        super(loginFailEnum.getMessage());
        this.code = loginFailEnum.getCode();
    }

}
