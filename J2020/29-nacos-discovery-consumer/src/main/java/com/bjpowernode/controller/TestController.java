package com.bjpowernode.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.bjpowernode.sentinel.MyBlockHandlerClass;
import com.bjpowernode.service.EchoFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	//feign 的声明式调用
	@Autowired
	private EchoFeignService echoFeignService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/echo/{app}")
	public String echoAppName(@PathVariable("app") String app){
		//使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
		ServiceInstance serviceInstance = loadBalancerClient.choose("29-nacos-discovery-provider");

		//  http://192.168.0.104:18082/echo/{app}
		String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), app);
		System.out.println("request url:"+url);

		return restTemplate.getForObject(url, String.class);
	}

	@GetMapping("/echo-rest/{str}")
	public String rest(@PathVariable String str) {

		//restTemplate + @LoadBalanced 实现远程调用
		return restTemplate.getForObject("http://29-nacos-discovery-provider/echo/" + str, String.class);
	}

	@GetMapping("/index")
	public String index(HttpServletRequest request) throws Exception {
		/*for (int i=0; i<2; i++) {
			String result = restTemplate.getForObject("http://29-nacos-discovery-provider/", String.class);
			System.out.println("调用远程服务结果：" + System.currentTimeMillis() + result);
			//TimeUnit.SECONDS.sleep(1);
		}*/
		System.out.println("X-Request-Id = " + request.getHeader("X-Request-Id"));

		System.out.println("X-Request-red = " + request.getHeader("X-Request-red"));

		System.out.println("color = " + request.getParameter("color"));

		return restTemplate.getForObject("http://29-nacos-discovery-provider/", String.class);
	}

	@GetMapping("/test")
	public String test() {
        //try {
			//Thread.sleep(250);
			//int a = 10 / 0;
        /*} catch (Exception e) {
            e.printStackTrace();
			*//*if (!BlockException.isBlockException(e)) {
				Tracer.trace(e);
			}*//*
        }*/
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	@PostMapping("/test1")
	public String test1() {
		//try {
		//Thread.sleep(250);
		//int a = 10 / 0;
        /*} catch (Exception e) {
            e.printStackTrace();
			*//*if (!BlockException.isBlockException(e)) {
				Tracer.trace(e);
			}*//*
        }*/
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	@GetMapping("/test2/{app}")
	public String test2(@PathVariable("app") String app) {
		System.out.println("/test2/{app} --> " + app);
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	@GetMapping("/test3/{app}")
	public String test3(@PathVariable("app") String app) {
		System.out.println("/test3/{app} --> " + app);

		ContextUtil.enter("test3");
		Entry entry = null;
		try {
			entry = SphU.entry("test3");

			//受sentinel保护的代码 start
			int a = 10 / 0;
			return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
			//受sentinel保护的代码 end

		} catch (BlockException e) {
			e.printStackTrace();
			//手动写上服务降级的代码
			if (e instanceof FlowException) {
				return "限流了...";
			} else if (e instanceof DegradeException) {
				return "熔断降级了...";
			}
			return "熔断了.....";
			//............

		} catch (ArithmeticException e) {
			//对 int a = 10 / 0; 异常的监控
			Tracer.trace(e);
			return "除数不能为0";
		} finally {
			if (entry != null) {
				entry.exit();
			}
			ContextUtil.exit();
		}
	}

	@GetMapping("/test4/{app}")
	@SentinelResource(value = "test4")
	public String test4(@PathVariable("app") String app) {
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	/**
	 * blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class 处理限流和降级
	 * fallback = "fallback", fallbackClass = MyFallbackClass.class 处理限流和降级
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	@GetMapping("/app") // 埋点：加入sentinel的监控
	//@SentinelResource(value = "app", fallback = "fallback", fallbackClass = MyFallbackClass.class)

	@SentinelResource(value = "app", blockHandler = "block", blockHandlerClass = MyBlockHandlerClass.class)
	public String app(@RequestParam(value = "a", required = false) String a,
					  @RequestParam(value = "b", required = false) String b) {
		System.out.println("/app/--> " + a + "--" + b);
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	@GetMapping("/test2")
	public String test2() {
		return restTemplate.getForObject("http://29-nacos-discovery-provider/test", String.class);
	}

	@GetMapping("/sleep")
	public String sleep() {
		return restTemplate.getForObject("http://29-nacos-discovery-provider/sleep", String.class);
	}

	@GetMapping("/notFound-feign")
	public String notFound() {
		return echoFeignService.notFound();
	}

	@GetMapping("/divide-feign")
	public String divide(@RequestParam Integer a, @RequestParam Integer b) {
		return echoFeignService.divide(a, b);
	}

	@GetMapping("/divide-feign2")
	public String divide(@RequestParam Integer a) {
		return echoFeignService.divide(a);
	}

	@GetMapping("/echo-feign/{str}")
	public String feign(@PathVariable String str) {
		return echoFeignService.echo(str);
	}

	@GetMapping("/services/{service}")
	public Object client(@PathVariable String service) {
		return discoveryClient.getInstances(service);
	}

	@GetMapping("/services")
	public Object services() {
		System.out.println(discoveryClient.description());
		System.out.println(discoveryClient.getOrder());
		return discoveryClient.getServices();
	}
}