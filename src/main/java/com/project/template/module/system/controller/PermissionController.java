package com.project.template.module.system.controller;

import com.project.template.common.result.Result;
import com.project.template.module.system.model.entity.Permission;
import com.project.template.module.system.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资源权限表,包含所有资源和对应的权限 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/permission")
@Tag(name = "权限模块")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/insert")
    @Operation(summary = "添加权限")
    public Result<Void> insert(@RequestParam @Parameter(description = "权限编号") String code,
                               @RequestParam @Parameter(description = "资源编号") String resourceCode,
                               @RequestParam @Parameter(description = "资源类型:menu-菜单资源,button-按钮资源,resource-自定义资源") String type,
                               @RequestParam @Parameter(description = "操作") String operation) {
        Permission build = Permission.builder()
                .code(code)
                .permissionCode(resourceCode.concat(":").concat(operation))
                .type(type)
                .build();
        permissionService.save(build);
        return Result.ok();
    }


}
