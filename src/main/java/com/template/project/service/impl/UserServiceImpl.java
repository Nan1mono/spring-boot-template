package com.template.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.template.project.mapper.dataSource2.UserMapper;
import com.template.project.model.entity.User;
import com.template.project.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    
    private UserMapper userMapper;
    
}
