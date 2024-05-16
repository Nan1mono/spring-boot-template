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
 * 账号/用户/管理员-角按钮关系表
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Getter
@Setter
@TableName("sys_user_button")
@Schema(name = "UserButton", description = "账号/用户/管理员-角按钮关系表")
public class UserButton extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "按钮id")
    @TableField("button_id")
    private Long buttonId;
}
