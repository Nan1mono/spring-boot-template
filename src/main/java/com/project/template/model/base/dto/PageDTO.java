package com.project.template.model.base.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageDTO {
    private Integer pageNum;
    private Integer pageSize;

    @JsonIgnore
    public Page getPage() {
        return com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO.of(pageNum, pageSize);
    }
}
