package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.MenuMapper;
import com.project.template.module.system.model.entity.Menu;
import com.project.template.module.system.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单资源表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
