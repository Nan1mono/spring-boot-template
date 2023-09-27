package com.project.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.mapper.RoleMenuMapper;
import com.project.template.model.entity.RoleMenu;
import com.project.template.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色-菜单关系表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-27
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
