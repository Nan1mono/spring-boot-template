package com.project.template.controller;

import com.project.template.common.exception.MyException;
import com.project.template.common.result.Result;
import com.project.template.common.result.ResultCodeEnum;
import com.project.template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/login")
@RestController
@Tag(name = "登录")
public class LoginController {

    @Resource
    private UserService userService;

    @Resource
    private UserDetailsService userDetailsService;

    @GetMapping("/auth")
    @Operation(summary = "登录")
    public Result<UserDetails> auth(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!userDetails.getPassword().equals(password)) {
            throw new MyException(ResultCodeEnum.PASSWORD_ERROR);
        }
        return Result.ok(userDetails);
    }

}
