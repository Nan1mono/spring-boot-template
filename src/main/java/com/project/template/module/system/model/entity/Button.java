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
 * 
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("sys_button")
@Schema(name = "Button", description = "按钮资源表,控制被限制的按钮")
public class Button extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "菜单id")
    @TableField("menu_id")
    private Long menuId;

    @Schema(description = "按钮编号")
    @TableField("button_code")
    private String buttonCode;

    @Schema(description = "按钮名称")
    @TableField("button_name")
    private String buttonName;

    @Schema(description = "描述")
    @TableField("remark")
    private String remark;

    @Schema(description = "状态 1启用 0停用")
    @TableField("status")
    private Integer status;
}
