package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 总配置
 */
@Configuration
@ComponentScan("com")//删除对应xml部分
@Import(DataSourceConfiguration.class)//引入数据源配置（可以引入多个类）
public class SpringConfiguration {

}
