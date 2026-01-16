package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2025-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User Object", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @ApiModelProperty(value = "primary key")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonProperty
    @ApiModelProperty(value = "account")
    private String no;

    @JsonProperty
    @ApiModelProperty(value = "name")
    private String name;

    @JsonProperty
    @ApiModelProperty(value = "password")
    private String password;
    @JsonProperty
    private Integer age;

    @JsonProperty
    @ApiModelProperty(value = "sex")
    private Integer sex;

    @JsonProperty
    @ApiModelProperty(value = "phone")
    private String phone;

    @JsonProperty
    @ApiModelProperty(value = "role")
    private Integer roleId;

    @JsonProperty
    @ApiModelProperty(value = "validity")
    @TableField("isValid")
    private String isvalid;
}
