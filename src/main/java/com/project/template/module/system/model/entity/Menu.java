package com.project.template.module.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.module.base.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单资源表
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("sys_menu")
@Schema(name = "Menu", description = "菜单资源表")
public class Menu extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "菜单编号")
    @TableField("menu_code")
    private String menuCode;

    @Schema(description = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @Schema(description = "父级菜单id")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "菜单层级")
    @TableField("level")
    private Integer level;

    @Schema(description = "主要路径")
    @TableField("path")
    private String path;

    @Schema(description = "路由路径")
    @TableField("uri")
    private String uri;

    @Schema(description = "顺序")
    @TableField("sorted")
    private Integer sorted;

    @Schema(description = "状态 1启用 0停用")
    @TableField("status")
    private Integer status;
}
