package com.project.template.module.system.controller;

import com.project.template.common.result.Result;
import com.project.template.module.system.model.entity.RolePermission;
import com.project.template.module.system.service.RolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色-权限关系表 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-09-28
 */
@RestController
@RequestMapping("/rolePermission")
@Tag(name = "权限分配模块")
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;

    @Autowired
    public RolePermissionController(RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @GetMapping("/insert")
    @Operation(summary = "分配权限")
    public Result<Void> insert(@RequestParam @Parameter(description = "角色id") Long roleId,
                               @RequestParam  @Parameter(description = "权限id集合") List<Long> permissionIdList) {
        List<RolePermission> list = Lists.newArrayList();
        for (Long permissionId : permissionIdList) {
            RolePermission permission = RolePermission.builder().roleId(roleId).permissionId(permissionId).build();
            list.add(permission);
        }
        rolePermissionService.saveBatch(list);
        return Result.ok();
    }

}
