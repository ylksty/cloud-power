package com.bjpowernode.controller;

import com.bjpowernode.model.Users;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "springboot使用swagger测试")
@RestController
public class SwaggerController {

    @ApiOperation(value = "获取用户信息", notes = "根据id来获取用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID",  dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "status", value = "用户状态", dataType = "Integer"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持"),
    })

    @RequestMapping(value = "/swagger/user/{id}/{status}", method = RequestMethod.GET)
    public @ResponseBody Users getUserInfo(@PathVariable("id") Integer id, @PathVariable("status") Integer status) {
        Users users = new Users();
        users.setId(id);
        users.setNick("cat");
        users.setPhone("1370000000");
        users.setPassword("******");
        users.setEmail("cat@163.com");
        users.setAccount("NO68958886878664");
        return users;
    }

    @ApiOperation(value = "添加用户信息", notes = "将用户信息提交保存到数据库")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "users", value = "用户对象", dataTypeClass = Users.class)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持"),
    })
    @RequestMapping(value = "/swagger/users", method = RequestMethod.POST)
    public @ResponseBody Users postUserInfo(@RequestBody Users users) {
        return users;
    }

    @ApiOperation(value = "修改用户信息", notes = "将用户信息修改保存到数据库")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "users", value = "用户对象", dataTypeClass = Users.class)
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持"),
    })
    @RequestMapping(value = "/swagger/users", method = RequestMethod.PUT)
    public @ResponseBody Users putUserInfo(@RequestBody Users users) {
        return users;
    }

    @ApiOperation(value = "删除户信息", notes = "将用户信息从数据库删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持"),
    })
    @ApiIgnore(value = "忽略这个api")
    @ExternalDocs(value = "扩展文档", url = "http://www.baidu.com")
    @RequestMapping(value = "/swagger/users", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteUserInfo(@RequestParam("id") Integer id) {
        return 100;
    }
}