package com.project.template.common.constant;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    DELETED(1, "已删除"),
    UNDELETED(0, "未删除"),
    ENABLE(1, "启用"),
    DISABLE(0, "停用"),
    LOCKED(1, "已锁定"),
    UN_LOCKED(0, "未锁定"),
    ;

    private final Integer code;
    private final String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
