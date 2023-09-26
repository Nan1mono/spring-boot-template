package com.project.template.security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SecurityButton {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "菜单id")
    private Long menuId;

    @Schema(description = "按钮编号")
    private String buttonCode;

    @Schema(description = "按钮名称")
    private String buttonName;

}
