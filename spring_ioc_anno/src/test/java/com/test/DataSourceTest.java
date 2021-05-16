package com.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.util.ResourceBundle;

/**
 * 数据库配置文件耦合版本
 */
public class DataSourceTest {
    @Test
    //测试手动创建c3p0数据源
    public void test1() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shop");//需要有shop数据库
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        Connection conn = dataSource.getConnection();
        //只验证是否连接成功
        System.out.println(conn);
        conn.close();//归还数据库连接池
    }

    @Test
    //测试手动创建druid数据源
    public void test2() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/shop");//需要有shop数据库
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        Connection conn = dataSource.getConnection();
        //只验证是否连接成功
        System.out.println(conn);
        conn.close();//归还数据库连接池
    }

    @Test
    //测试手动创建druid数据源(使用配置文件jdbc.properties)
    public void test3() throws Exception {
        //读取配置文件
        ResourceBundle rb = ResourceBundle.getBundle("jdbc");
        String driver = rb.getString("jdbc.driver");
        String url = rb.getString("jdbc.url");
        String username = rb.getString("jdbc.username");
        String password = rb.getString("jdbc.password");

        //创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        Connection conn = dataSource.getConnection();
        //只验证是否连接成功
        System.out.println(conn);
        conn.close();//归还数据库连接池
    }

    //测试Spring容器产生数据源对象(Druid数据源为例)
    //见applicationContext.xml
    @Test
    public void test4() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        DruidDataSource druidDataSource = app.getBean(DruidDataSource.class);
        Connection conn = druidDataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }

    @Test
    //抽取jdbc
    public void test5() throws Exception {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext1.xml");
        DruidDataSource druidDataSource = app.getBean(DruidDataSource.class);
        Connection conn = druidDataSource.getConnection();
        System.out.println(conn);
        conn.close();
    }
}
