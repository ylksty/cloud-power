package com.bjpowernode.springboot.autoconfig;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@ConditionalOnClass(SqlSessionFactory.class) //当classpath下有某个class的时候，就执行下面操作
@Configuration
@Import(value = {MyDataSourceProperties.class})
public class MyBatisAutoConfiguration {

    /**
     *  <!--激活读取properties文件-->
     * 	<context:property-placeholder location="classpath:jdbc.properties"/>
     */
    //MyDataSourceProperties完成了

    /**
     * <!--配置数据源-->
     * <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" destroy-method="close">
     * <property name="driverClassName" value="${jdbc.driverClassName}" />
     * <property name="url" value="${jdbc.url}" />
     * <property name="username" value="${jdbc.username}" />
     * <property name="password" value="${jdbc.password}" />
     * </bean>
     *
     * @param myDataSourceProperties
     * @return
     */
    @Bean
    public DruidDataSource druidDataSource(MyDataSourceProperties myDataSourceProperties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(myDataSourceProperties.getDriverClassName());
        druidDataSource.setUrl(myDataSourceProperties.getUrl());
        druidDataSource.setUsername(myDataSourceProperties.getUsername());
        druidDataSource.setPassword(myDataSourceProperties.getPassword());
        return druidDataSource;
    }

    /**
     * <!--配置mybatis工厂bean：SqlSessionFactoryBean-->
     * <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
     * <property name="dataSource" ref="dataSource"/>
     * <property name="configLocation" value="classpath:mybatis-config.xml"/>
     * </bean>
     *
     * @return
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DruidDataSource druidDataSourced) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(druidDataSourced);
        return sqlSessionFactoryBean;
    }

    /**
     * <!--配置mybatis mapper包扫描-->
     * <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
     * <!--mapper接口变成spring容器的bean对象-->
     * <property name="basePackage" value="com.bjpowernode.mapper"/>
     * <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
     * </bean>
     *
     * @param environment
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(Environment environment) {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage(environment.getProperty("spring.mybatis.power.base-package"));
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * <!--配置数据源事务管理器,spring框架高度抽象出来的一个类-->
     * <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
     * <property name="dataSource" ref="dataSource"/>
     * </bean>
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DruidDataSource druidDataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(druidDataSource);
        return dataSourceTransactionManager;
    }
}
//我什么时候配置LettuceConnection信息
