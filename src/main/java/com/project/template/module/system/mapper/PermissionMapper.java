package com.project.template.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.module.system.model.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 资源权限表,包含所有资源和对应的权限 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
