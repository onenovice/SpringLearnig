package com.test;

import com.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ Description:测试
 * @ Author: Jay
 * @ Date: Create in 18:51 2021/5/7
 * @ Version:
 */
public class SpringTest {
    @Test
    //测试scope
    public void test1() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao1 = (UserDao) app.getBean("userDao");
        UserDao userDao2 = (UserDao) app.getBean("userDao");
        //scope是singleton时地址相同说明只有一个对象（对象在加载配置文件时创建）
        //scope时prototype时，对象在getBean()时创建
        System.out.println(userDao1);
        System.out.println(userDao2);
    }

    @Test
    //测试初始化方法和销毁方法
    //init-method="init" destroy-method="destroy"
    public void test2() {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDao) app.getBean("userDao");
        System.out.println(userDao);
        //单元测试，不知何时会销毁，没来得及打印销毁，除非手动关闭
        //((ClassPathXmlApplicationContext)app).close();

    }
}
