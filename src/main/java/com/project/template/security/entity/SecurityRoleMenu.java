package com.project.template.security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SecurityRoleMenu {

    @Schema(description = "菜单id")
    private Long menuId;

    @Schema(description = "菜单编号")
    private String menuCode;

    @Schema(description = "菜单名称")
    private String menuName;

    @Schema(description = "菜单层级")
    private Integer level;

    @Schema(description = "父级菜单id")
    private Long parentId;

    @Schema(description = "主要路径")
    private String path;

    @Schema(description = "路由路径")
    private String uri;

    @Schema(description = "顺序")
    private Integer sorted;

    @Schema(description = "子菜单")
    private List<SecurityRoleMenu> roleMenuList;


}
