package com.project.template.model.base.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageDateDTO implements Serializable {

    private Integer pageNum;
    private Integer pageSize;


    @ApiModelProperty("开始时间")
    private String starTime;


    @ApiModelProperty("结束时间")
    private String endTime;

    @JsonIgnore
    public Page getPage() {
        return com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO.of(pageNum, pageSize);
    }
    
}
