package com.project.template.service.impl;

import com.project.template.model.entity.UserButton;
import com.project.template.mapper.UserButtonMapper;
import com.project.template.service.UserButtonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
