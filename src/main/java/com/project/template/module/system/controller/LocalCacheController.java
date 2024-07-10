package com.project.template.module.system.controller;

import com.project.template.common.helper.LocalCacheHelper;
import com.project.template.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/localCache")
@RestController
@Tag(name = "本地缓存")
public class LocalCacheController {

    @GetMapping("/put")
    @Operation(summary = "存储")
    public Result<Void> put(String key, String value) {
        LocalCacheHelper.put(key, value);
        return Result.ok();
    }

    @GetMapping("/get")
    @Operation(summary = "获取")
    public Result<Object> get(String key) {
        Object value = LocalCacheHelper.get(key);
        return Result.ok(value);
    }

}
