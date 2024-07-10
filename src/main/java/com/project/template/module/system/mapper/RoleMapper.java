package com.project.template.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.module.system.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
