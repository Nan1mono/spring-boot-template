package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.RoleMapper;
import com.project.template.module.system.model.entity.Role;
import com.project.template.module.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
