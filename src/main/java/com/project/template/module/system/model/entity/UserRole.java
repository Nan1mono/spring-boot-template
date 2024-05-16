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
 * 账号/用户/管理员-角色关系表
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Getter
@Setter
@TableName("sys_user_role")
@Schema(name = "UserRole", description = "账号/用户/管理员-角色关系表")
public class UserRole extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "角色id")
    @TableField("role_id")
    private Long roleId;
}
