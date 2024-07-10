package com.project.template.module.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.module.base.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * <p>
 * 角色-权限关系表
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Getter
@Setter
@TableName("sys_role_permission")
@Schema(name = "RolePermission", description = "角色-权限关系表")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Long roleId;

    @Schema(description = "权限id")
    @TableField("permission_id")
    private Long permissionId;
}
