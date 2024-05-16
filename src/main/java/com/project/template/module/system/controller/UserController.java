package com.project.template.module.system.controller;

import com.project.template.common.result.Result;
import com.project.template.common.util.SecurityUtils;
import com.project.template.module.system.model.entity.User;
import com.project.template.module.system.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-09-22
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getById")
    @Operation(summary = "根据id获取user")
    public Result<User> getById(@RequestParam Long id) {
        return Result.ok(userService.getById(id));
    }

    @PostMapping("/insert")
    @Operation(summary = "添加User")
    public Result<Void> insert(@RequestBody User user) {
        String password = user.getPassword();
        String encryptedPassword = SecurityUtils.encryptPassword(password);
        user.setPassword(encryptedPassword);
        userService.save(user);
        return Result.ok();
    }

}
