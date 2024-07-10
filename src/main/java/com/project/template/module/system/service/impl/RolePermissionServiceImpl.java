package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.RolePermissionMapper;
import com.project.template.module.system.model.entity.RolePermission;
import com.project.template.module.system.service.RolePermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-权限关系表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

}
