package com.bjpowernode.controller;

import com.bjpowernode.model.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TemplateController {

    @RequestMapping("/service/hello")
    public String hello(HttpServletRequest request) {
        String header = request.getHeader("X-Request-Id");

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/hello -->" + header);
        return "Hello, Spring Cloud，Provider";
    }

    @RequestMapping("/service/user")
    public User user() {
        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/user");

        User user = new User();
        user.setId(108);
        user.setName("张三丰");
        user.setPhone("13800000000");

        return user;
    }

    @RequestMapping("/service/getUser")
    public User getUser(@RequestParam("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("phone") String phone) {

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/getUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        return user;
    }

    //@RequestMapping(value="/service/addUser", method = RequestMethod.POST)
    @PostMapping("/service/addUser")
    public User addUser(@RequestParam("id") Integer id,
                        @RequestParam("name") String name,
                        @RequestParam("phone") String phone) {

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/addUser");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        //将user对象插入数据库（暂时省略）
        return user;
    }

    //@RequestMapping(value="/service/addUser", method = RequestMethod.POST)
    @PostMapping("/service/addUser2")
    public User addUser2(@RequestBody User user,
                         @RequestParam("token") String token,
                         @RequestParam("encode") String encode) {
        //进行业务处理（省略）
        System.out.println(token + "---" + encode);
        System.out.println("服务提供者1-->/service/addUser-->" + user.getId() + "--" + user.getName() + "--" + user.getPhone());

        //将user对象插入数据库（暂时省略）
        return user;
    }

    //@RequestMapping(value="/service/addUser", method = RequestMethod.POST)
    @PostMapping("/service/addUser3")
    public User addUser3(@RequestBody User user) {
        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/addUser-->" + user.getId() + "--" + user.getName() + "--" + user.getPhone());

        //将user对象插入数据库（暂时省略）
        return user;
    }

    //@RequestMapping(value="/service/addUser", method = RequestMethod.PUT)
    @PutMapping("/service/updateUser")
    public User updateUser(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone) {

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/updateUser--" + id + "--" + name + "--" + phone);

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        //将user对象插入数据库（暂时省略）
        return user;
    }

    //@RequestMapping(value="/service/addUser", method = RequestMethod.DELETE)
    @DeleteMapping("/service/deleteUser")
    public User deleteUser(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone) {

        //进行业务处理（省略）
        System.out.println("服务提供者1-->/service/deleteUser--" + id + "--" + name + "--" + phone);

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);

        // http://xxx.xxx.xxx:9090/api/user/1 --查询、删除
        //RequestMappring(value="/api/user/{id}", method=RequestMethod.GET) --调用查询功能
        //RequestMappring(value="/api/user/{id}", method=RequestMethod.DELETE) --调用删除功能

        //将user对象插入数据库（暂时省略）
        return user;
    }
}