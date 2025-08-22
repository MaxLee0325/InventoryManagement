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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonProperty
    @ApiModelProperty(value = "账号")
    private String no;

    @JsonProperty
    @ApiModelProperty(value = "名字")
    private String name;

    @JsonProperty
    @ApiModelProperty(value = "密码")
    private String password;
    @JsonProperty
    private Integer age;

    @JsonProperty
    @ApiModelProperty(value = "性别")
    private Integer sex;

    @JsonProperty
    @ApiModelProperty(value = "电话")
    private String phone;

    @JsonProperty
    @ApiModelProperty(value = "角色 0超级管理员，1管理员，2普通账号")
    private Integer roleId;

    @JsonProperty
    @ApiModelProperty(value = "是否有效，Y有效，其他无效")
    @TableField("isValid")
    private String isvalid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
