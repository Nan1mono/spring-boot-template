package com.project.template.common.constant;

import lombok.Getter;

@Getter
public enum UserStatusEnum {

    DELETED(1, "已删除"),
    UNDELETED(0, "未删除"),
    ENABLE(1, "启用"),
    DISABLE(0, "停用");

    private final Integer code;
    private final String message;

    UserStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
