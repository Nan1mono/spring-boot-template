package com.template.project.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.template.project.mapper.dataSource2", sqlSessionFactoryRef = "SessionFactory2")
public class DataSource2Config {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource2")
    public DataSource dataSource2() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory SessionFactory2(@Qualifier("dataSource2") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        // 指定主库
        // 两种写法均可以，一种是直接从容器中获取已经注入的bean名称（通过@Qualifier）获取dataSource对象
        // 另一种是直接调用dataSource方法获取dataSource对象
//        sessionFactory.setDataSource(dataSource2());
        sessionFactory.setDataSource(dataSource);
        // 指定主库对应的mapper.xml文件
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/dataSource2/*.xml"));
        return sessionFactory.getObject();
    }
}
