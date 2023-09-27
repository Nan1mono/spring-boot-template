package com.project.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.mapper.PermissionMapper;
import com.project.template.model.entity.Permission;
import com.project.template.service.PermissionService;
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
