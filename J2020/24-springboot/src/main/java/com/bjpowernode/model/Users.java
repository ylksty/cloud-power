package com.bjpowernode.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@NoArgsConstructor
//@Getter
//@Setter
@Data
@ApiModel(value = "User", description = "User对象")
public class Users {

    @ApiModelProperty(value = "用户ID", name = "ID")
    private int id;

    @ApiModelProperty(value = "用户昵称", name = "nick")
    private String nick;

    private String phone;

    private String password;

    private String email;

    private String account;

}