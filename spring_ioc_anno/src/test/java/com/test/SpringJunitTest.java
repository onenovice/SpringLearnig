package com.test;

import com.config.SpringConfiguration;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Spring集成Junit测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext1.xml")//配置文件配置文件形式
@ContextConfiguration(classes = {SpringConfiguration.class})//全注解形式时

public class SpringJunitTest {
    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        userService.save();
    }

    @Autowired
    private DataSource dataSource;

    @Test
    //DataSource测试
    public void test2() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
