package com.service.impl;

import com.dao.UserDao;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * <bean id="userService" class="com.service.impl.UserServiceImpl">
 * <property name="userDao" ref="userDao"></property>
 * </bean>
 */
//@Component("userService")
@Service("userService")
@Scope("singleton")
public class UserServiceImpl implements UserService {
    //测试@Value注解
    //jdbc.driver全限定名
    @Value("${jdbc.driver}")
    private String driver;

    @Autowired//只使用该注解不搭配@Qualifier("userDao")，是按照数据类型从Spring容器进行匹配
    @Qualifier("userDao")//是按照id的名称从容器匹配，但是使用时必须搭配 @Autowired
    //@Resource("userDao")//等价：@Autowired + @Qualifier("userDao")
    private UserDao userDao;

    //使用注解方式。set方法可以删除
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println(driver);//注入普通属性实例，测试时刻注释掉
        userDao.save();
    }

    @PostConstruct
    public void init() {
        System.out.println("Service对象初始化方法");
    }

    @PreDestroy
    //看不到效果
    public void destroy() {
        System.out.println("Service对象销毁方法");
    }

}
