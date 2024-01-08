package com.project.template.common.util.md5.enums;

import lombok.Getter;

@Getter
public enum MD5FailEnums {

    FAIL(300, "MD5加密出错"),
    ;

    private final Integer code;
    private final String message;

    MD5FailEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
