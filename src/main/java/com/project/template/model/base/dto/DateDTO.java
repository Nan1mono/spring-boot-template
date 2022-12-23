package com.project.template.model.base.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DateDTO {
    @ApiModelProperty("开始时间")
    private String starTime;
    
    @ApiModelProperty("结束时间")
    private String endTime;
}
