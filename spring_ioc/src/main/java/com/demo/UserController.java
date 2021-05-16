package com.demo;

import com.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 用来简单示例，其实不这么写
 */
public class UserController {
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) app.getBean("userService");
        userService.save();

/*      没从容器取，会因为private UserDao userDao使得调用时报空指针异常
        UserService userService1 = new UserServiceImpl();
        userService1.save();*/
    }
}
