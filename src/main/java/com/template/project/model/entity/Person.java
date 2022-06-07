package com.template.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.alibaba.excel.annotation.ExcelProperty;

/**
* @author nanimono
* @since 2022-06-07
*/
@ApiModel(value ="")
@Data
    @EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Person implements Serializable {

private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "档案编号")
                @TableId(value = "person_id", type = IdType.AUTO)
                private Long personId;

    @ApiModelProperty(value = "姓名")
        private String personName;

    @ApiModelProperty(value = "年龄")
        private Integer personAge;

    @ApiModelProperty(value = "性别 0未知 1男 2女")
        private Integer personSex;

    @ApiModelProperty(value = "电话")
        private String personTel;

    @ApiModelProperty(value = "身份证号码")
        private String personIdNumber;

    @ApiModelProperty(value = "备注")
        private String remarks;

    @ApiModelProperty(value = "添加人")
        private String personAddPeo;

    @ApiModelProperty(value = "创建时间")
        private LocalDateTime createdOn;

    @ApiModelProperty(value = "创建人")
        private String createdBy;

    @ApiModelProperty(value = "更新时间")
        private LocalDateTime updateOn;

    @ApiModelProperty(value = "更新人")
        private String updateBy;

    @ApiModelProperty(value = "扩展编码1")
        private String edFieldFirstCode;

    @ApiModelProperty(value = "扩展描述1")
        private String edFieldFirstDesc;

    @ApiModelProperty(value = "扩展编码2 用于独居标记 是 否")
        private String edFieldSecondCode;

    @ApiModelProperty(value = "扩展描述2 用于家庭情况概述")
        private String edFieldSecondDesc;

    @ApiModelProperty(value = "扩展编码3")
        private String edFieldThirdCode;

    @ApiModelProperty(value = "扩展描述3")
        private String edFieldThirdDesc;

    @ApiModelProperty(value = "扩展编码4")
        private String edFieldFourthCode;

    @ApiModelProperty(value = "扩展描述4")
        private String edFieldFourthDesc;

    @ApiModelProperty(value = "扩展编码5")
        private String edFieldFifthCode;

    @ApiModelProperty(value = "扩展描述5")
        private String edFieldFifthDesc;

    @ApiModelProperty(value = "状态 1正常 2停用 -1删除")
        private Integer status;



}
