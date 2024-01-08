package com.project.template.common.util.md5.exception;

import com.project.template.common.util.md5.enums.MD5FailEnums;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MD5Exception extends RuntimeException {

    private final Integer code;

    public MD5Exception(String message){
        super(message);
        this.code = null;
    }

    public MD5Exception(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public MD5Exception(MD5FailEnums md5FailEnums) {
        super(md5FailEnums.getMessage());
        this.code = md5FailEnums.getCode();
    }

}
