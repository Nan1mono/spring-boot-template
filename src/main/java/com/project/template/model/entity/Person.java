package com.project.template.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.project.template.model.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2022-12-23
 */
@Getter
@Setter
@TableName("person")
@ApiModel(value = "Person对象", description = "")
public class Person extends BaseEntity {

    @TableId(value = "person_id", type = IdType.AUTO)
    @ApiModelProperty("personId")
    private Long personId;

    @TableField("person_name")
    @ApiModelProperty(value = "person姓名")
    private String personName;
}
