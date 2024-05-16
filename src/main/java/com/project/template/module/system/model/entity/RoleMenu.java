package com.project.template.module.system.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.module.base.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色-菜单关系表
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Getter
@Setter
@TableName("sys_role_menu")
@Schema(name = "RoleMenu", description = "角色-菜单关系表")
public class RoleMenu extends BaseEntity {

    @Schema(description = "id")
    @TableId("id")
    private Long id;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "菜单id")
    @TableField("menu_id")
    private Long menuId;
}
