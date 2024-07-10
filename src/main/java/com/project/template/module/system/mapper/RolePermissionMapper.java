package com.project.template.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.module.system.model.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> findUserPermission(@Param("roleIdList") List<Long> roleIdList);

}
