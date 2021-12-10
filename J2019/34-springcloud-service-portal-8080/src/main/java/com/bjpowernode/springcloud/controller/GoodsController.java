package com.bjpowernode.springcloud.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.springcloud.constants.Constant;
import com.bjpowernode.springcloud.model.Goods;
import com.bjpowernode.springcloud.model.Orders;
import com.bjpowernode.springcloud.model.ResultObject;
import com.bjpowernode.springcloud.model.Users;
import com.bjpowernode.springcloud.service.GoodsRemoteClient;
import com.bjpowernode.springcloud.service.OrdersRemoteClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RefreshScope
@Controller
public class GoodsController {

    //产品服务的接口地址（直连）
    private static final String GOODS_SERVICE_URL = "http://localhost:9100/service/goods";

    //产品服务的接口地址 （注册中心服务名）
    private static final String GOODS_SERVICE_URL_02 = "http://34-SPRINGCLOUD-SERVICE-GOODS/service/goods";

    //feign的远程调用客户端34-SPRINGCLOUD-SERVICE-GOODS
    @Autowired
    private GoodsRemoteClient goodsRemoteClient;

    @Autowired
    private OrdersRemoteClient ordersRemoteClient;

    /**
     * 此类可以进行http接口的调用，除此之外还有apache httpClient, java.net.URL相关类也可以
     *
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 读取远程配置中心的配置信息
     */
    @Value("${eureka.client.service-url.defaultZone}")
    private String defaultZone;

    /**
     * 读取远程配置中心的配置信息
     */
    @Value("${info.address}")
    private String address;

    /**
     * HttpHeaders这个bean注入到controller中
     *
     */
    @Autowired
    private HttpHeaders httpHeaders;

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goods1")
    public @ResponseBody
    ResultObject goods1() {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }

    @RequestMapping("/cloud/goods2")
    public @ResponseBody ResultObject goods2() {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用
        //ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);
        ResponseEntity<ResultObject> responseEntity = restTemplate.exchange(GOODS_SERVICE_URL_02, HttpMethod.GET, new HttpEntity<Object>(httpHeaders), ResultObject.class);
        return responseEntity.getBody();
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goodsFeign")
    public @ResponseBody Object goodsFeign(Model model) {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        ResultObject resultObject = goodsRemoteClient.goods();

        return resultObject;
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback",
            commandProperties={
                    @HystrixProperty(name="execution.timeout.enabled", value="true"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")
            }, ignoreExceptions = Throwable.class)
    @RequestMapping("/cloud/goodsHystrix")
    public @ResponseBody ResultObject goodsHystrix() {
        System.out.println("/cloud/goodsHystrix-->8080");
        //1、服务超时，会降级
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //2、服务异常，会降级
        /*String str = null;
        if (str == null) {
            throw new RuntimeException("服务异常了.");
        }*/

        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        return goodsRemoteClient.goods();
    }

    /**
     * 降级方法 (备用的方法)
     *
     * @return
     */
    public ResultObject fallback(Throwable throwable) {
        throwable.printStackTrace();
        System.out.println(throwable.getMessage());
        return new ResultObject(Constant.ONE, "服务降级了.");
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/cloud/goodsRibbon")
    public @ResponseBody ResultObject goodsRibbon() {
        System.out.println("/cloud/goodsRibbon-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback",
                    threadPoolKey = "goods",
                    threadPoolProperties = {
                        @HystrixProperty(name = "coreSize", value = "2"),
                        @HystrixProperty(name = "maxQueueSize", value = "1")
                    })
    @RequestMapping("/cloud/goodsLimit")
    public @ResponseBody ResultObject goodsLimit() {
        System.out.println("/cloud/goodsLimit-->8080");
        //调用远程的一个controller, restful的调用
        ResponseEntity<ResultObject> responseEntity = restTemplate.getForEntity(GOODS_SERVICE_URL_02, ResultObject.class);

        return responseEntity.getBody();
    }

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping("/cloud/goodsFeignHystrix")
    public @ResponseBody ResultObject goodsFeignHystrix() {
        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样
        return goodsRemoteClient.goods();
    }

    /**
     * 读取远程配置中心的配置
     *
     * @return
     */
    @RequestMapping("/cloud/getConfig")
    public @ResponseBody Object getConfig() {
        return defaultZone + " --- " + address;
    }

    //------------下面是案例测试业务方法------------

    /**
     * 查询所有商品
     *
     * @return
     */
    @RequestMapping(value = "/cloud/goods", method = RequestMethod.GET)
    public String goods(Model model) {
        System.out.println("/cloud/goods-->8080");
        //调用远程的一个controller, restful的调用，通过feign这种声明式的远程调用，goodsRemoteClient就像dubbo里面的接口层一样

        ResultObject resultObject = goodsRemoteClient.goods();

        JSONArray jsonArray = JSONObject.parseArray(JSONObject.toJSONString(resultObject.getData()));
        List<Goods> goodsList = jsonArray.toJavaList(Goods.class);

        model.addAttribute("goodsList", goodsList);
        return "goods";
    }

    /**
     * 查询商品详情
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/cloud/goods/{id}", method = RequestMethod.GET)
    public String detail (Model model, @PathVariable("id") Integer id) {
        System.out.println("/cloud/detail-->8080，id=" + id);
        //假设用户已经登录成功，把用户放入session
        Users users = new Users();
        users.setId(88888888);

        //根据商品id获取商品的信息
        ResultObject resultObject = goodsRemoteClient.goodsDetail(id);

        model.addAttribute("goods", JSONObject.parseObject(JSONObject.toJSONString(resultObject.getData()), Goods.class));

        //跳转到thymeleaf模板页面
        return "detail";
    }

    /**
     * 立即购买
     *
     * @param id
     * @param buyNum
     * @return
     */
    @RequestMapping(value = "/cloud/goods/{id}", method = RequestMethod.POST)
    public @ResponseBody ResultObject submitBuy (@PathVariable("id") Integer id,
                                                 @RequestParam("buyNum") Integer buyNum) {
        System.out.println("/cloud/goods/{id}-->8080，id=" + id + "，buyNum=" + buyNum);

        //假设用户已经登录成功，把用户放入session
        Users users = new Users();
        users.setId(88888888);

        //根据商品id和购买数量减库存 （goods微服务）
        goodsRemoteClient.decrByStore(id, buyNum);

        //下订单 （orders微服务）
        Orders orders = new Orders();
        orders.setBuynum(buyNum);
        orders.setGoodsid(id);
        orders.setUid(users.getId());
        orders.setCreatetime(new Date());
        ordersRemoteClient.order(users.getId(), id, buyNum, orders);

        //TODO 如果考虑严谨，需要分布式事务，此处我们省略，分布式事务尽量少用，
        // 一个大型的系统，也只会在极其关键的地方使用分布式事务

        return new ResultObject(Constant.ZERO, "下单成功，请立即支付");
    }

    @RequestMapping(value = "/cloud/orders", method = RequestMethod.GET)
    public @ResponseBody Object orders () {
        System.out.println("/cloud/orders-->8080");

        //根据商品id获取商品的信息
        ResultObject resultObject = ordersRemoteClient.orders();

        return resultObject;
    }
}