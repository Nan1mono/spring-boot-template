package com.project.template.security.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityUserRole {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "角色编号")
    private String roleCode;

    @Schema(description = "角色名称")
    private String roleName;

}
