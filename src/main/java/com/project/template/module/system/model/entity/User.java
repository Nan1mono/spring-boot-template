package com.project.template.module.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.module.base.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2023-09-22
 */
@Getter
@Setter
@TableName("sys_user")
@Schema(name = "User", description = "账号/用户/管理员表")
public class User extends BaseEntity {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "真实姓名")
    @TableField("real_name")
    private String realName;

    @Schema(description = "出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @Schema(description = "用户名/账号")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "密码到期时间 空代表永不过期")
    @TableField("pwd_expiration_time")
    private LocalDateTime pwdExpirationTime;

    @Schema(description = "状态 1启用 0停用")
    @TableField("status")
    private Integer status;

    @Schema(description = "是否被锁定 0锁定 1锁定")
    @TableField("is_locked")
    private Integer isLocked;

    @Schema(description = "账号锁定到期时间，空代表永久锁定")
    @TableField("lock_datetime")
    private LocalDateTime lockDatetime;
}
