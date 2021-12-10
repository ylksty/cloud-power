package com.bjpowernode.controller;

import com.bjpowernode.config.MyInfo;
import com.bjpowernode.model.Goods;
import com.bjpowernode.service.GoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Lazy(value = false) //当前controller是不会延迟初始化的
@Controller
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;

    @Value("${my.info.desc}")
    private String desc;

    @Autowired
    private MyInfo myInfo;

    @Autowired
    private Environment environment;

    @RequestMapping("/index")
    public String index(Model model) {
        int a = 10 / 0;

        //返回jsp页面
        return "index";
    }

    @RequestMapping("/goods")
    public String goods(Model model) throws FileNotFoundException {

        FileOutputStream fileOutputStream = new FileOutputStream("c:/dd/.txt");

        Goods goods = goodsService.selectByPrimaryKey(1);
        model.addAttribute("goods", goods);

        //返回jsp页面
        return "goods";
    }

    @RequestMapping("/web/test")
    public @ResponseBody Object test() {
        //int a = 10 / 0; //抛出异常，显示一个背景白色的错误页面，不是很友好
        return "cors " + desc + myInfo.getPhone() + environment.getProperty("my.info.address");
    }

    @RequestMapping("/web/jsonp")
    public @ResponseBody Object jsonp() {
        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v3");
        return "handleResponse({\"k1\":\"v1\",\"k2\":\"v3\"})";
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @RequestMapping("/web/page")
    public String page() {
        //int a = 10 / 0; //抛出异常，显示一个背景白色的错误页面，不是很友好
        //return "page";
        return null;
    }
}