package com.bjpowernode.controller;

import com.bjpowernode.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TemplateController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${service.name}")
	private String serviceName;

	@GetMapping("/web/{app}")
	public String echoAppName(@PathVariable("app") String app){

		//使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
		ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);

		//  http://192.168.0.104:18082/echo/{app}
		String url = String.format("http://%s:%s/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), app);

		//restTemplate发起调用
		return restTemplate.getForObject(url, String.class);
	}

	/**
	 * Get请求，无参数，返回String
	 *
	 * @return
	 */
	@RequestMapping("/web/hello")
	public String hello () {
		//逻辑判断（省略）

		//调用SpringCloud服务提供者提供的服务
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(serviceName + "/service/hello", String.class);

		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus httpStatus = responseEntity.getStatusCode();
		HttpHeaders httpHeaders = responseEntity.getHeaders();
		String body = responseEntity.getBody();

		System.out.println(statusCodeValue);
		System.out.println(httpStatus);
		System.out.println(httpHeaders);
		System.out.println(body);

		return responseEntity.getBody();
	}

	/**
	 * Get请求，无参数，返回User
	 *
	 * @return
	 */
	@RequestMapping("/web/user")
	public User user () {
		//逻辑判断（省略）

		//调用SpringCloud服务提供者提供的服务
		ResponseEntity<User> responseEntity = restTemplate.getForEntity(serviceName + "/service/user", User.class);
		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus httpStatus = responseEntity.getStatusCode();
		HttpHeaders httpHeaders = responseEntity.getHeaders();
		User body = responseEntity.getBody();

		System.out.println(statusCodeValue);
		System.out.println(httpStatus);
		System.out.println(httpHeaders);
		System.out.println(body.getId() + "--" + body.getName() + "--" + body.getPhone());

		return restTemplate.getForEntity(serviceName + "/service/user", User.class).getBody();
	}

	/**
	 * Get请求，有参数，返回User
	 *
	 * @return
	 */
	@RequestMapping("/web/getUser")
	public User getUser () {
		//逻辑判断（省略）

		String[] strArray = {"105", "张无忌", "13600000000"};

		Map<String, Object> paramMap = new ConcurrentHashMap<>();
		paramMap.put("id", 1028);
		paramMap.put("name", "张翠山");
		paramMap.put("phone", "13700000000");

		//调用SpringCloud服务提供者提供的服务
		ResponseEntity<User> responseEntity1 = restTemplate.getForEntity(serviceName + "/service/getUser?id={0}&name={1}&phone={2}", User.class, strArray);

		ResponseEntity<User> responseEntity2 = restTemplate.getForEntity(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap);

		/*int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpStatus httpStatus = responseEntity.getStatusCode();
        HttpHeaders httpHeaders = responseEntity.getHeaders();
        User body = responseEntity.getBody();

        System.out.println(statusCodeValue);
        System.out.println(httpStatus);
        System.out.println(httpHeaders);*/

		User body1 = restTemplate.getForObject(serviceName + "/service/getUser?id={0}&name={1}&phone={2}", User.class, strArray);
		User body2 = restTemplate.getForObject(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap);

		System.out.println(body1.getId() + "--" + body1.getName() + "--" + body1.getPhone());
		System.out.println(body2.getId() + "--" + body2.getName() + "--" + body2.getPhone());

		return restTemplate.getForEntity(serviceName + "/service/getUser?id={id}&name={name}&phone={phone}", User.class, paramMap).getBody();
	}

	/**
	 * POST请求，有参数，返回User -addUser方法
	 *
	 * @return
	 */
	@RequestMapping("/web/addUser")
	public User addUser () {
		//逻辑判断（省略）
		String[] strArray = {"105", "张无忌", "13600000000"};

		Map<String, Object> paramMap = new ConcurrentHashMap<>();
		paramMap.put("id", 1028);
		paramMap.put("name", "张翠山");
		paramMap.put("phone", "13700000000");

		//要传的表单信息，参数数据（很坑人的），用普通HashMap包装参数，传不过去
		MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<String, Object>();
		dataMap.add("id", "1028");
		dataMap.add("name", "张翠山");
		dataMap.add("phone", "13700000000");

		//调用SpringCloud服务提供者提供的服务  http://29-nacos-discovery-provider/service/addUser
		ResponseEntity<User> responseEntity = restTemplate.postForEntity(serviceName + "/service/addUser", dataMap, User.class);

		int statusCodeValue = responseEntity.getStatusCodeValue();
		HttpStatus httpStatus = responseEntity.getStatusCode();
		HttpHeaders httpHeaders = responseEntity.getHeaders();
		User body1 = responseEntity.getBody();
		System.out.println(body1.getId() + "--" + body1.getName() + "--" + body1.getPhone());

		System.out.println(statusCodeValue);
		System.out.println(httpStatus);
		System.out.println(httpHeaders);

		User body2 = restTemplate.postForObject(serviceName + "/service/addUser", dataMap, User.class);
		System.out.println(body2.getId() + "--" + body2.getName() + "--" + body2.getPhone());

		User user = new User();
		user.setId(1099);
		user.setName("谢逊");
		user.setPhone("15800000000");
		User body3 = restTemplate.postForObject(serviceName + "/service/addUser2?token={token}&encode={encode}", user, User.class, "123456", "utf-8");
		System.out.println(body3.getId() + "--" + body3.getName() + "--" + body3.getPhone());

		//restTemplate调用传输json
		String userJSON = "{\"id\" : 1088, \"name\" : \"殷素素\", \"phone\" : \"13900000000\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(userJSON, headers);
		User body4 = restTemplate.postForObject(serviceName + "/service/addUser3", entity, User.class);
		System.out.println(body4.getId() + "--" + body4.getName() + "--" + body4.getPhone());

		return restTemplate.postForEntity(serviceName + "/service/addUser", dataMap, User.class).getBody();
	}

	/**
	 * 消费者调用 -updateUser方法
	 *
	 * @return
	 */
	@RequestMapping("/web/updateUser")
	public String updateUser () {

		//逻辑判断（省略）

		String[] strArray = {"105", "张无忌", "13600000000"};

		Map<String, Object> paramMap = new ConcurrentHashMap<>();
		paramMap.put("id", 1028);
		paramMap.put("name", "张翠山");
		paramMap.put("phone", "13700000000");


		//要传的表单信息，参数数据（很坑人的）
		MultiValueMap<String, Object> dataMap = new LinkedMultiValueMap<String, Object>();
		dataMap.add("id", "1028");
		dataMap.add("name", "张翠山");
		dataMap.add("phone", "13700000000");

		//调用SpringCloud服务提供者提供的服务
		restTemplate.put(serviceName + "/service/updateUser", dataMap);

		restTemplate.put(serviceName + "/service/updateUser", dataMap);

		return "success";
	}

	/**
	 * 消费者调用 -deleteUser方法
	 *
	 * @return
	 */
	@RequestMapping("/web/deleteUser")
	public String deleteUser () {
		//逻辑判断（省略）

		String[] strArray = {"105", "张无忌", "13600000000"};

		Map<String, Object> paramMap = new ConcurrentHashMap<>();
		paramMap.put("id", 1028);
		paramMap.put("name", "张翠山");
		paramMap.put("phone", "13700000000");

		//调用SpringCloud服务提供者提供的服务
		restTemplate.delete(serviceName + "/service/deleteUser?id={0}&name={1}&phone={2}", strArray);

		restTemplate.delete(serviceName + "/service/deleteUser?id={id}&name={name}&phone={phone}", paramMap);


		// service/deleteUser
		return "success";
	}
}