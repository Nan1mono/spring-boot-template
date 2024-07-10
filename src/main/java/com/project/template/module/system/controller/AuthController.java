package com.project.template.module.system.controller;

import com.project.template.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/login")
@RestController
@Tag(name = "登录")
public class AuthController {

    private final AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    public AuthController(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @GetMapping("/auth")
    @Operation(summary = "登录")
    public Result<Authentication> auth(@RequestParam String username, @RequestParam String password) throws Exception {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, password);
        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
        return Result.ok(authenticationManager.authenticate(authentication));
    }

    @GetMapping("/authTest")
    @Operation(summary = "权限测试接口")
    @PreAuthorize("hasAuthority('menu_on_page_one_add_button:view')")
    public Result<String> authTest() {
        return Result.ok("请求成功");
    }

}
