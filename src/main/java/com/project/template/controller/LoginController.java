package com.project.template.controller;

import com.project.template.common.result.Result;
import com.project.template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Resource
    private AuthenticationConfiguration authenticationConfiguration;

    @GetMapping("/auth")
    @Operation(summary = "登录")
    public Result<Authentication> auth(String username, String password) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        bCryptPasswordEncoder.upgradeEncoding(password);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return Result.ok(authenticationManager.authenticate(authentication));
    }

}
