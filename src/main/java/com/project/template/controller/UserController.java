package com.project.template.controller;

import com.project.template.common.result.Result;
import com.project.template.model.entity.User;
import com.project.template.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/getById")
    @Operation(summary = "根据id获取user")
    @PreAuthorize("hasAnyAuthority('user:view')")
    public Result<User> getById(@RequestParam Long id) {
        return Result.ok(userService.getById(id));
    }

    @PostMapping("/insert")
    @Operation(summary = "添加User")
    public Result<Void> insert(@RequestBody User user){
        userService.save(user);
        return Result.ok();
    }

}
