package com.project.template.module.system.controller;

import com.project.template.logger.entity.TemplateLog;
import com.project.template.logger.service.TemplateLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Tag(name = "日志模块")
public class LoggerController {

    private final TemplateLogService templateLogService;

    @Autowired
    public LoggerController(TemplateLogService templateLogService) {
        this.templateLogService = templateLogService;
    }

    @GetMapping("/list")
    @Operation(summary = "获取日志")
    public Page<TemplateLog> listLog(@RequestParam(required = false) String uri, @RequestParam(required = false)String method,
                                     @RequestParam(required = false)String ip, @RequestParam(required = false)Integer httpStatus,
                                     Integer page, Integer size){
        return templateLogService.list(uri, method, ip, httpStatus, page, size);
    }

}
