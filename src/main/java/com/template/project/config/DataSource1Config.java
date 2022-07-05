package com.template.project.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.formula.functions.Intercept;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.template.project.mapper.dataSource1",
        sqlSessionFactoryRef = "SessionFactory1")
public class DataSource1Config {

    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource1")
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory SessionFactory1(@Qualifier("dataSource1") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        // 指定主库
        // 两种写法均可以，一种是直接从容器中获取已经注入的bean名称（通过@Qualifier）获取dataSource对象
        // 另一种是直接调用dataSource方法获取dataSource对象
//        sessionFactory.setDataSource(dataSource1());
        sessionFactory.setDataSource(dataSource);
        // 指定主库对应的mapper.xml文件
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/dataSource1/*.xml"));
        // 集成分页插件
        Interceptor[] intercepts = {mybatisPlusInterceptor};
        sessionFactory.setPlugins(intercepts);
        // 添加sql控制台打印
        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        mybatisConfiguration.setLogImpl(StdOutImpl.class);
        sessionFactory.setConfiguration(mybatisConfiguration);
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager1() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource1());
        return dataSourceTransactionManager;
    }
}
