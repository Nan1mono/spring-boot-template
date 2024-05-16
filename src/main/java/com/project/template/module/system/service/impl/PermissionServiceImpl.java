package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.PermissionMapper;
import com.project.template.module.system.model.entity.Permission;
import com.project.template.module.system.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源权限表,包含所有资源和对应的权限 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
