package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 数据原配置，注解开发
 */
@PropertySource("classpath:jdbc.properties")//数据源
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;


    @Bean("dataSource")//Spring会将当前方法的返回值以指定名称存储到Spring容器
    public DataSource getDataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);//需要有shop数据库
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
