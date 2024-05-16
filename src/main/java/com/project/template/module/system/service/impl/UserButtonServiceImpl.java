package com.project.template.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.template.module.system.mapper.UserButtonMapper;
import com.project.template.module.system.model.entity.UserButton;
import com.project.template.module.system.service.UserButtonService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号/用户/管理员-角按钮关系表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-09-26
 */
@Service
public class UserButtonServiceImpl extends ServiceImpl<UserButtonMapper, UserButton> implements UserButtonService {

}
