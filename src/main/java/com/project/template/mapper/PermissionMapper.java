package com.project.template.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.template.model.entity.Permission;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 资源权限表,包含所有资源和对应的权限 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

}
