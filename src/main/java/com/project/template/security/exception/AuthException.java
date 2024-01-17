package com.project.template.security.exception;

import com.project.template.security.enums.AuthFailEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public AuthException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public AuthException(AuthFailEnum authFailEnum) {
        super(authFailEnum.getMessage());
        this.code = authFailEnum.getCode();
    }


}
