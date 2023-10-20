package com.project.template.security.exception;

import com.project.template.security.enums.LoginEnum;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginException extends RuntimeException {

    /**
     * 错误码
     */
    private final Integer code;

    public LoginException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public LoginException(LoginEnum loginEnum) {
        super(loginEnum.getMessage());
        this.code = loginEnum.getCode();
    }


}
