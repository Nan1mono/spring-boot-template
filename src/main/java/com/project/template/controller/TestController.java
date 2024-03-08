package com.project.template.controller;

import com.project.template.common.result.Result;
import com.project.template.schedule_job.entity.ScheduleJob;
import com.project.template.schedule_job.entity.bo.ScheduleCondition;
import com.project.template.schedule_job.service.ScheduleActionTemplate;
import com.project.template.schedule_job.service.ScheduleDataTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@Tag(name = "测试模块")
public class TestController {

    private final ScheduleActionTemplate scheduleActionTemplate;

    private final ScheduleDataTemplate scheduleDataTemplate;

    @Autowired
    public TestController(ScheduleActionTemplate scheduleActionTemplate,
                          ScheduleDataTemplate scheduleDataTemplate) {
        this.scheduleActionTemplate = scheduleActionTemplate;
        this.scheduleDataTemplate = scheduleDataTemplate;
    }

    @GetMapping("/getUri")
    @Operation(summary = "获取uri")
    public Result<List<String>> getURI() {
        return Result.ok();
    }

    @GetMapping("/quartz/pause")
    @Operation(summary = "暂停定时任务")
    public Result<Void> quartzDelete(@RequestParam Long id) {
        scheduleActionTemplate.pause(id);
        return Result.ok();
    }

    @GetMapping("/quartz/resume")
    @Operation(summary = "恢复定时任务")
    public Result<Void> resume(@RequestParam Long id) {
        scheduleActionTemplate.resume(id);
        return Result.ok();
    }

    @GetMapping("/quartz/page")
    @Operation(summary = "分页查询定时任务")
    public Result<Page<ScheduleJob>> page() {
        ScheduleCondition condition = new ScheduleCondition();
        condition.setPageNum(1);
        condition.setPageSize(10);
        return Result.ok(scheduleDataTemplate.page(condition));
    }

}
