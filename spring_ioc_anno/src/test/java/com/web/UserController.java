package com.web;

import com.config.SpringConfiguration;
import com.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @ Description:
 * @ Author: Jay
 * @ Date: Create in 16:39 2021/5/10
 * @ Version:
 */
public class UserController {
    public static void main(String[] args) {
        //ApplicationContext app= new ClassPathXmlApplicationContext("applicationContext1.xml");
        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);

        UserService userService = app.getBean(UserService.class);
        userService.save();
    }
}
