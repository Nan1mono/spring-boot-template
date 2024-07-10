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
 * 角色表
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("sys_role")
@Schema(name = "Role", description = "角色表")
public class Role extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色编号")
    @TableField("role_code")
    private String roleCode;

    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "描述")
    @TableField("remark")
    private String remark;

    @Schema(description = "状态 1启用 0停用")
    @TableField("status")
    private Integer status;
}
