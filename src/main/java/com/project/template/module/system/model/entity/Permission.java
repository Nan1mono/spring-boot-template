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
 * 资源权限表,包含所有资源和对应的权限
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Getter
@Setter
@TableName("sys_permission")
@Schema(name = "Permission", description = "资源权限表,包含所有资源和对应的权限")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "对应的资源编号")
    @TableField("code")
    private String code;

    @Schema(description = "资源类型:menu-菜单资源,button-按钮资源,resource-自定义资源")
    @TableField("type")
    private String type;

    @Schema(description = "权限编号")
    @TableField("permission_code")
    private String permissionCode;

    @Schema(description = "状态 1启用 0停用")
    @TableField("status")
    private Integer status;
}
