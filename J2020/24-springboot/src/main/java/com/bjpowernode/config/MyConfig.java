package com.bjpowernode.config;

import com.bjpowernode.Application;
import com.bjpowernode.filter.MyFilter;
import com.bjpowernode.inteceptor.MyInterceptor;
import com.bjpowernode.listener.MyListener;
import com.bjpowernode.servlet.MyServlet;
import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableAsync //开启异步执行
//@ComponentScan("cn.xx")
@Configuration
public class MyConfig extends SpringBootServletInitializer implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MyConfig.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/web/**");
        registry.addMapping("/boot/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //对来自 /api/** 链接的请求进行拦截，对登录请求/api/login不进行拦截
        String[] addPathPatterns = {"/**", "/api/**", "/boot/**"};
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns("/api/login", "/web/test");

        //另外一个拦截器
        /*registry.addInterceptor(new HeInterceptor())
                .addPathPatterns(addPathPatterns)
                .excludePathPatterns("/api/login", "/web/test");*/
    }

    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/web/**");
                registry.addMapping("/boot/**");
            }
        };
    }*/

    /**
     * 可以覆盖springboot中默认的线程池
     *
     * @return
     */
    /*@Bean
    public ThreadPoolTaskExecutor asyncExecutor() {
        logger.info("start asyncExecutor......");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(16);
        //配置最大线程数
        executor.setMaxPoolSize(64);
        //配置队列大小
        executor.setQueueCapacity(9999);
        //配置线程池中的线程的名称前缀 (指定一下线程名的前缀)
        executor.setThreadNamePrefix("async-order-");
        // rejection-policy：当pool已经达到max pool size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }*/

    @Bean
    public ServletRegistrationBean heServletRegistrationBean(){
        ServletRegistrationBean registration = new 	ServletRegistrationBean(new MyServlet(), "/myServlet");
        return registration;
    }

    @Bean
    public FilterRegistrationBean heFilterRegistration() {
        FilterRegistrationBean registration = new 	FilterRegistrationBean(new MyFilter());
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public ServletListenerRegistrationBean listenerRegistrationBean() {
        ServletListenerRegistrationBean registration = new 	ServletListenerRegistrationBean(new MyListener());
        return registration;
    }

    /**
     * 编程方式实现springboot的http
     *
     * @return
     */
    /*@Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        return tomcat;
    }*/

    /**
     * 创建http连接器
     *
     * @return
     */
    private Connector createHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        // http端口
        connector.setPort(8080);
        connector.setSecure(false);
        // https端口
        connector.setRedirectPort(8443);
        return connector;
    }

    /**
     * 编程方式实现springboot的https
     *
     * @return
     */
    /*@Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> containerCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory container) {
                Ssl ssl = new Ssl();
                //服务器私钥和证书
                String path = MyConfig.class.getClassLoader().getResource("server.jks").getPath();
                ssl.setKeyStore(path);
                ssl.setKeyStorePassword("123456");
                ssl.setKeyStoreType("jks"); //通过配置文件读进来 @Value("${}")
                container.setSsl(ssl);
                container.setPort(8443);
            }
        };
    }*/
}