package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.UserRoleMapper;
import com.project.template.module.system.model.entity.UserRole;
import com.project.template.module.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号/用户/管理员-角色关系表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
