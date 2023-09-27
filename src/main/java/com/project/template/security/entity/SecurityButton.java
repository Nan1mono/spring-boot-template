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

    @Schema(description = "菜单编号")
    private String menuCode;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "主要路径")
    private String path;

    @Schema(description = "路由路径")
    private String uri;

    @Schema(description = "按钮编号")
    private String buttonCode;

    @Schema(description = "按钮名称")
    private String buttonName;

}
