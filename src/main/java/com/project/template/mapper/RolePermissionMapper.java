package com.project.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.model.entity.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Repository
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    List<String> findUserPermission(@Param("roleIdList") List<Long> roleIdList);

}
