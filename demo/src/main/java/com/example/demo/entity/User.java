package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    @JsonProperty
    private int id;
    @JsonProperty
    private String no;
    @JsonProperty
    private String name;
    @JsonProperty
    private String password;
    @JsonProperty
    private int sex;
    @JsonProperty
    private int roleId;
    @JsonProperty
    private String phone;
    @JsonProperty
    @TableField("isvalid")
    private String isValid;
}