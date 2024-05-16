package com.project.template.module.system.controller;

import com.project.template.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@Tag(name = "测试模块")
public class TestController {

    @GetMapping("/getUri")
    @Operation(summary = "获取uri")
    public Result<List<String>> getURI() {
        return Result.ok();
    }

}
