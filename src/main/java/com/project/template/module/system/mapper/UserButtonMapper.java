package com.project.template.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.module.system.model.entity.UserButton;
import com.project.template.security.entity.SecurityButton;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账号/用户/管理员-角按钮关系表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Mapper
public interface UserButtonMapper extends BaseMapper<UserButton> {

    List<SecurityButton> findUserButton(@Param("userId") Long userId);

}
