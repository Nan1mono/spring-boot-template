package com.project.template.service.impl;

import com.project.template.model.entity.Menu;
import com.project.template.mapper.MenuMapper;
import com.project.template.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
