package com.project.template.service.impl;

import com.project.template.model.entity.Role;
import com.project.template.mapper.RoleMapper;
import com.project.template.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
